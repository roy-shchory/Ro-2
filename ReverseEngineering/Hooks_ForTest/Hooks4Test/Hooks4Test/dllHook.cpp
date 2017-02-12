// dllHook.cpp : Defines the exported functions for the DLL application.
//

#include "stdafx.h"
#include <windows.h>
#include <stdio.h>

typedef void(*HOOK_FUNCTION_TYPE)(void);

void setHook(LPCTSTR dllModuleName, LPCSTR functionToHookName, HOOK_FUNCTION_TYPE myHook, int jmpBackCmdOffset, bool useFakeFunction);
void setIATHook(LPCTSTR exeModuleName, LPCTSTR dllModuleName, LPCSTR functionToHookName, int offsetOfIatEntry, HOOK_FUNCTION_TYPE myHook, int jmpBackCmdOffset, bool useFakeFunction);
void setRegularHook(LPCTSTR dllModuleName, LPCSTR functionToHookName, int bytesToCopy, HOOK_FUNCTION_TYPE myHook, int jmpBackCmdOffset, bool useFakeFunction);

/**************************************************************************************************
This pointer will automaticly be set to the start of the real function
***************************************************************************************************/
LPVOID startOfRealFunction = 0;







/**************************************************************************************************
CAN EDIT THIS
---------------------------------------------------------------------------------------------------
Some MACROs
***************************************************************************************************/
#define BYTES_TO_COPY_MAX 20 // >= 5

/**************************************************************************************************
CAN EDIT THIS
---------------------------------------------------------------------------------------------------
The fake function - if needed
***************************************************************************************************/
typedef COLORREF (*SetTextColorFuncType)(_In_ HDC, _In_ COLORREF);

COLORREF SetTextColorFake(
	_In_ HDC      hdc,
	_In_ COLORREF crColor
	) {
	SetTextColorFuncType realFunc = SetTextColorFuncType(startOfRealFunction);
	return realFunc(hdc, 0x00FF0000);
}

/**************************************************************************************************
CAN EDIT THIS
---------------------------------------------------------------------------------------------------
The hooks
***************************************************************************************************/
__declspec(naked) void setColorHook_WithFake()
{    
	//OutputDebugString(p);
	__asm {
		nop
		mov eax , [esp+0x20]
		and eax,0x00FFFFFF
		mov [esp+0x8], eax
		mov eax, [esp + 8]
		push eax
		mov eax, [esp + 8]
		push eax
		call SetTextColorFake
		add esp, 8
		ret 8
	}
}

__declspec(naked) void setColorHook()
{
	__asm {
		nop
			mov eax, [esp + 0x20]
			and eax, 0x00FFFFFF
			mov eax, 0x00000FF0
			mov[esp + 0x8], eax
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

/**************************************************************************************************
CAN EDIT THIS
---------------------------------------------------------------------------------------------------
Create Hooks
create all the hooks from this function
***************************************************************************************************/
void createHooks() {
	// for all hooks
	LPCTSTR DLL_MODULE_NAME = L"gdi32.dll";
	LPCSTR FUNCTION_TO_HOOK_NAME = "SetTextColor";
	int MY_HOOK_FUNCTION_JMP_BACK_OFFSET = 0x1f;
	HOOK_FUNCTION_TYPE MY_HOOK = setColorHook;
	bool USE_FAKE = false;

	if (USE_FAKE)
		MY_HOOK = setColorHook_WithFake;

	// for IAT hook only
	LPCTSTR EXE_MODULE_NAME = L"calc.exe";
	int OFFSET_OF_IAT_ENTRY = 0x1014;

	// for regular hook only
	int BYTES_TO_COPY = 8; // >= 5




	//setHook(DLL_MODULE_NAME, FUNCTION_TO_HOOK_NAME, MY_HOOK, MY_HOOK_FUNCTION_JMP_BACK_OFFSET, USE_FAKE);
	//setIATHook(EXE_MODULE_NAME, DLL_MODULE_NAME, FUNCTION_TO_HOOK_NAME, OFFSET_OF_IAT_ENTRY, MY_HOOK, MY_HOOK_FUNCTION_JMP_BACK_OFFSET, USE_FAKE);
	setRegularHook(DLL_MODULE_NAME, FUNCTION_TO_HOOK_NAME, BYTES_TO_COPY, MY_HOOK, MY_HOOK_FUNCTION_JMP_BACK_OFFSET, USE_FAKE);
}










/**************************************************************************************************
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
---------------------------------------------------------------------------------------------------
DON'T CHANGE ANYTHING FROM THIS POINT
---------------------------------------------------------------------------------------------------
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
***************************************************************************************************/





/**************************************************************************************************
Some Aux Functions
***************************************************************************************************
***************************************************************************************************/
void writeJmpOpcodeFromHook_WithAddedCode(HOOK_FUNCTION_TYPE hookFunc, int offsetInHook, LPVOID jumpToAddr, CHAR* codeToWriteBeforeJMP, int sizeOfCode) {
	DWORD lpProtect = 0;
	CHAR  JmpOpcodeWithEAX[8] = "\xB8\x90\x90\x90\x90\xFF\xE0";
	int sizeOfAddedCode = 0;

	if (codeToWriteBeforeJMP != NULL && sizeOfCode > 0) {
		sizeOfAddedCode = sizeOfCode;
	}

	memcpy(JmpOpcodeWithEAX + 1, &jumpToAddr, 0x4);

	VirtualProtect((char*)hookFunc + offsetInHook, 0x7 + sizeOfAddedCode, PAGE_EXECUTE_READWRITE, &lpProtect);
	if (sizeOfAddedCode > 0) {
		memcpy((char*)hookFunc + offsetInHook, codeToWriteBeforeJMP, sizeOfAddedCode);
	}
	memcpy((char*)hookFunc + offsetInHook + sizeOfAddedCode, &JmpOpcodeWithEAX, 0x7);
	VirtualProtect((char*)hookFunc + offsetInHook, 0x7 + sizeOfAddedCode, PAGE_EXECUTE_READ, &lpProtect);
}

void writeJmpOpcodeFromHook(HOOK_FUNCTION_TYPE hookFunc, int offsetInHook, LPVOID jumpToAddr) {
	writeJmpOpcodeFromHook_WithAddedCode(hookFunc, offsetInHook, jumpToAddr, NULL, -1);
}

/**************************************************************************************************
Hook With HOT-Patching
***************************************************************************************************
***************************************************************************************************/
void setHook(LPCTSTR dllModuleName, LPCSTR functionToHookName, HOOK_FUNCTION_TYPE myHook, int jmpBackCmdOffset, bool useFakeFunction) {
	LPVOID f;
	HMODULE h = GetModuleHandle(dllModuleName);
	CHAR JmpOpcode[6] = "\xE9\x90\x90\x90\x90";
    DWORD lpProtect = 0;
    LPVOID JumpTo;

	if (h == NULL) {
		return;
	}
	f = GetProcAddress(h , functionToHookName);
	if (f == NULL) {
		return;
	}
	// calculate relative jump to myHook from f
    JumpTo = (LPVOID)((char*)myHook - (char*)f);
	memcpy(JmpOpcode + 1, &JumpTo, 0x4);

	// write the jump
    VirtualProtect((char*)f-5, 0x7, PAGE_EXECUTE_READWRITE, &lpProtect);  
	memcpy((char*)f-5, &JmpOpcode, 0x5);

	// override the first two bytes with jmp short
	*(char*)f = 0xEB;
	*((char*)(f)+1) = 0xf9;

    VirtualProtect((char*)f-5, 0x7, PAGE_EXECUTE_READ, &lpProtect);

	// jump to f from myHook
	JumpTo = (LPVOID)((char*)f + 2);
	startOfRealFunction = JumpTo;
	if (useFakeFunction)
		return;
	writeJmpOpcodeFromHook(myHook, jmpBackCmdOffset, JumpTo);
}

/**************************************************************************************************
Hook From IAT
***************************************************************************************************
***************************************************************************************************/
void setIATHook(LPCTSTR exeModuleName, LPCTSTR dllModuleName, LPCSTR functionToHookName, 
	int offsetOfIatEntry, 
	HOOK_FUNCTION_TYPE myHook, int jmpBackCmdOffset, bool useFakeFunction) {

	LPVOID f;
	HMODULE h = GetModuleHandle(exeModuleName);
	HMODULE h2 = GetModuleHandle(dllModuleName);
    DWORD lpProtect = 0;
    LPVOID JumpTo;
	LPVOID iat;

	if (h == NULL || h2 == NULL) {
		return;
	}
	
	f = GetProcAddress(h2 , functionToHookName);
	if (f == NULL) {
		return;
	}
	
	iat = h + offsetOfIatEntry/4;

	// write the address of myHook into the IAT
	JumpTo = (LPVOID)((char*)myHook);
	VirtualProtect((char*)iat, 0x4, PAGE_EXECUTE_READWRITE, &lpProtect);  
	memcpy(iat, &JumpTo, 0x4);  

	// jump to f from myHook
	JumpTo = (LPVOID)((char*)f);
	startOfRealFunction = JumpTo;
	if (useFakeFunction)
		return;
	writeJmpOpcodeFromHook(myHook, jmpBackCmdOffset, JumpTo);
}

/**************************************************************************************************
Regular Hook
***************************************************************************************************
***************************************************************************************************
TODO: change BYTES_TO_COPY so it will copy an integer number of commends (must be bigger or equal to 5)

This hook will not use hot-patching or the IAT

In REGULAR mode (useFakeFunction == 0):
BYTES_TO_COPY number of bytes will be copied from the start of the real function
to the end of the hook (just before jumping back to the rest of the real function)

In useFakeFunction mode:
BYTES_TO_COPY number of bytes will be copied from the start of the real function
to tempFunc (a function that will contain the copied commends and a jump to the real function)
TODO: make sure the number of NOPs in tempFunc is >= BYTES_TO_COPY + 0x7
***************************************************************************************************/
__declspec(naked) void tempFunc()
{
	__asm {
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
		nop
		nop
		nop
		nop
		nop
	}
}

void setRegularHook(LPCTSTR dllModuleName, LPCSTR functionToHookName, int bytesToCopy, HOOK_FUNCTION_TYPE myHook, int jmpBackCmdOffset, bool useFakeFunction) {
	LPVOID f;
	HMODULE h = GetModuleHandle(dllModuleName);
	CHAR JmpOpcode[BYTES_TO_COPY_MAX];
	CHAR CopiedCode[BYTES_TO_COPY_MAX]; //run this code before jumping back to the function
    DWORD lpProtect = 0;
    LPVOID JumpTo;
	int i;
	
	if (h == NULL) {
		return;
	}
	
	f = GetProcAddress(h , functionToHookName);
	if (f == NULL) {
		return;
	}
	
	// copy bytes from the start of f
	VirtualProtect((char*)f, bytesToCopy, PAGE_EXECUTE_READWRITE, &lpProtect); // maybe not needed
	memcpy(CopiedCode, f, bytesToCopy);
	VirtualProtect((char*)f, bytesToCopy, PAGE_EXECUTE_READ, &lpProtect);
	
	// init jmpOpcode - fill with NOPs (not really needed... we jump back after BYTES_TO_COPY)
	JmpOpcode[0] = '\xE9';
	for (i = 1; i < bytesToCopy; ++i) {
		JmpOpcode[i] = '\x90';
	}
	
	// calculate relative jump to myHook from f+offset
    JumpTo = (LPVOID)((char*)myHook - (char*)f - 0x5);
	memcpy(JmpOpcode + 1, &JumpTo, 0x4);

	// write the jump
    VirtualProtect((char*)f, bytesToCopy, PAGE_EXECUTE_READWRITE, &lpProtect);
	memcpy((char*)f, &JmpOpcode, bytesToCopy);
    VirtualProtect((char*)f, bytesToCopy, PAGE_EXECUTE_READ, &lpProtect);

	// jump to f from myHook
	JumpTo = (LPVOID)((char*)f + bytesToCopy);
	if (useFakeFunction) {
		startOfRealFunction = &tempFunc;
		writeJmpOpcodeFromHook_WithAddedCode(tempFunc, 0, JumpTo, CopiedCode, bytesToCopy);
	} else {
		startOfRealFunction = JumpTo;
		writeJmpOpcodeFromHook_WithAddedCode(myHook, jmpBackCmdOffset, JumpTo, CopiedCode, bytesToCopy);
	}
}

