// dllHook.cpp : Defines the exported functions for the DLL application.
//

#include "stdafx.h"
#include <windows.h>
#include <stdio.h>

// for all hooks
#define DLL_MODULE_NAME L"gdi32.dll"
#define FUNCTION_TO_HOOK_NAME "SetTextColor"

#define MY_HOOK_FUNCTION_MAX_SIZE 0x100
#define MY_HOOK_FUNCTION_JMP_BACK_OFFSET 0x1f

// for IAT hook only
#define EXE_MODULE_NAME L"calc.exe"
#define OFFSET_OF_IAT_ENTRY 0x1234

// for regular hook only
#define BYTES_TO_COPY 8 // >= 5

__declspec(naked) void myHook()
{    
	//OutputDebugString(p);
	__asm {
		nop
		mov eax , [esp+0x20]
		and eax,0x00FFFFFF
		mov [esp+0x8], eax
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
		nop
	}
}
__declspec(naked) void myHookEnd()        {} 

// aux functions
typedef void(*HOOK_FUNCTION_TYPE)(void);

void makeJmpOpcode(CHAR* opcodeArray, LPVOID adder) {
	// size(opcodeArray) >= 5
	opcodeArray[0] = '\xE9';
	memcpy(opcodeArray + 1, &adder, 0x4);
}

void add_jmp_back_from_hook(HOOK_FUNCTION_TYPE hookFunc, int offsetInHook, LPVOID jumpBackAddr) {
	DWORD lpProtect = 0;
	CHAR opcodeArray[5];

	makeJmpOpcode(opcodeArray, jumpBackAddr);
	VirtualProtect((char*)hookFunc + offsetInHook, 0x5, PAGE_EXECUTE_READWRITE, &lpProtect);
	memcpy((char*)hookFunc + MY_HOOK_FUNCTION_JMP_BACK_OFFSET, &opcodeArray, 0x5);
	VirtualProtect((char*)hookFunc + offsetInHook, 0x5, PAGE_EXECUTE_READ, &lpProtect);
}

// hook with hot-patching
void setHook() {
	LPVOID f;
	HMODULE h = GetModuleHandle(DLL_MODULE_NAME);
	CHAR JmpOpcode[5];
    DWORD lpProtect = 0;
    LPVOID JumpTo;

	
	if (h == NULL) {
		return;
	}
	f = GetProcAddress(h , FUNCTION_TO_HOOK_NAME);
	if (f == NULL) {
		return;
	}
	// calculate relative jump to myHook from f
    JumpTo = (LPVOID)((char*)&myHook - (char*)f);
	makeJmpOpcode(JmpOpcode, JumpTo);

	// write the jump
    VirtualProtect((char*)f-5, 0x7, PAGE_EXECUTE_READWRITE, &lpProtect);  
	memcpy((char*)f-5, &JmpOpcode, 0x5);

	// override the first two bytes with jmp short
	*(char*)f = 0xEB;
	*((char*)(f)+1) = 0xf9;

    VirtualProtect((char*)f-5, 0x7, PAGE_EXECUTE_READ, &lpProtect);

	// jump to f from myHook
	JumpTo = (LPVOID)((char*)f + 2);
	add_jmp_back_from_hook(myHook, MY_HOOK_FUNCTION_JMP_BACK_OFFSET, JumpTo);
}

// hook from IAT
void setIATHook() {
	LPVOID f;
	HMODULE h = GetModuleHandle(EXE_MODULE_NAME);
	HMODULE h2 = GetModuleHandle(DLL_MODULE_NAME);
	CHAR  JmpOpcode[6] = "\xE9\x90\x90\x90\x90";  
    CHAR  JmpOpcode2[8] = "\xB8\x90\x90\x90\x90\xFF\xE0";  
    DWORD lpProtect = 0;
    LPVOID CalculatedJump;
    LPVOID JumpTo;
	LPVOID iat;

	if (h == NULL || h2 == NULL) {
		return;
	}
	
	f = GetProcAddress(h2 , FUNCTION_TO_HOOK_NAME);
	if (f == NULL) {
		return;
	}
	
	iat = h + OFFSET_OF_IAT_ENTRY/4;

	// write the address of myHook into the IAT
	JumpTo = (LPVOID)((char*)&myHook);
	VirtualProtect((char*)iat, 0x4, PAGE_EXECUTE_READWRITE, &lpProtect);  
	memcpy(iat, &JumpTo, 0x4);  

	// jump to f from myHook
	JumpTo = (LPVOID)((char*)f);
	memcpy(JmpOpcode2 + 1, &f, 0x4);  
	VirtualProtect((char*)myHook, MY_HOOK_FUNCTION_MAX_SIZE, PAGE_EXECUTE_READWRITE, &lpProtect); 
	memcpy((char*)&myHook + MY_HOOK_FUNCTION_JMP_BACK_OFFSET, &JmpOpcode2, 0x7);
    VirtualProtect((char*)myHook, MY_HOOK_FUNCTION_MAX_SIZE, PAGE_EXECUTE_READ, &lpProtect);
}

// regular hook
void setRegularHook() {
	LPVOID f;
	HMODULE h = GetModuleHandle(DLL_MODULE_NAME);
	CHAR JmpOpcode[BYTES_TO_COPY]; // = "\xE9\x90\x90\x90\x90";
	CHAR CopiedCode[BYTES_TO_COPY]; //run this code before jumping back to the function
    CHAR JmpOpcode2[6] = "\xE9\x90\x90\x90\x90";
    DWORD lpProtect = 0;
    LPVOID JumpTo;
	int i;
	
	if (h == NULL) {
		return;
	}
	
	f = GetProcAddress(h , FUNCTION_TO_HOOK_NAME);
	if (f == NULL) {
		return;
	}
	
	// copy bytes from the start of f
	VirtualProtect((char*)f, BYTES_TO_COPY, PAGE_EXECUTE_READWRITE, &lpProtect); // maybe not needed
	memcpy(CopiedCode, f, BYTES_TO_COPY);
	VirtualProtect((char*)f, BYTES_TO_COPY, PAGE_EXECUTE_READ, &lpProtect);
	
	// init jmpOpcode - fill with NOPs (not really needed... we jump back after BYTES_TO_COPY)
	JmpOpcode[0] = '\xE9';
	for (i = 1; i < BYTES_TO_COPY; ++i) {
		JmpOpcode[i] = '\x90';
	}
	
	// calculate relative jump to myHook from f+offset
    JumpTo = (LPVOID)((char*)&myHook - (char*)f - 0x5);
	memcpy(JmpOpcode + 1, &JumpTo, 0x4);

	// write the jump
    VirtualProtect((char*)f, BYTES_TO_COPY, PAGE_EXECUTE_READWRITE, &lpProtect);
	memcpy((char*)f, &JmpOpcode, BYTES_TO_COPY);
    VirtualProtect((char*)f, BYTES_TO_COPY, PAGE_EXECUTE_READ, &lpProtect);

	// jump to f from myHook
	JumpTo = (LPVOID)((char*)f + BYTES_TO_COPY);
	memcpy(JmpOpcode2 + 1, &JumpTo, 0x4);
	
	VirtualProtect((char*)myHook, MY_HOOK_FUNCTION_MAX_SIZE, PAGE_EXECUTE_READWRITE, &lpProtect);
	memcpy((char*)&myHook + MY_HOOK_FUNCTION_JMP_BACK_OFFSET, &CopiedCode, BYTES_TO_COPY);
	memcpy((char*)&myHook + MY_HOOK_FUNCTION_JMP_BACK_OFFSET + BYTES_TO_COPY, &JmpOpcode2, 0x5);
    VirtualProtect((char*)myHook, MY_HOOK_FUNCTION_MAX_SIZE, PAGE_EXECUTE_READ, &lpProtect);   
}

// hook with hot-patching and wrapper
void setHookWrapper() {
	
}
