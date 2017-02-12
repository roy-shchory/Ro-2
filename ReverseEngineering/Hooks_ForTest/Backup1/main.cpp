#include <windows.h>
#include <stdio.h>
#include <tlhelp32.h>
#include <Shlwapi.h>


#define DLL_PATH "c:\\rev\\dllHook.dll"
#define true 1
#define false 0


BOOL dllInjector(char * dllpath , DWORD pID);


int WINAPI WinMain(HINSTANCE hInstance,HINSTANCE hPrevInstance,LPSTR lpCmdLine,int nCmdShow)
{

   // Create Process SUSPENDED
	PROCESS_INFORMATION pi;
	STARTUPINFOA Startup;
	ZeroMemory(&Startup, sizeof(Startup));
	ZeroMemory(&pi, sizeof(pi));
	CreateProcessA(lpCmdLine, NULL, NULL, NULL, NULL, CREATE_SUSPENDED, NULL, NULL, &Startup, &pi);


   if(!(dllInjector(DLL_PATH , pi.dwProcessId)))
      return 1;
   ResumeThread(pi.hThread);
   return 0;
}

BOOL dllInjector(char * dllpath , DWORD pID)
{
   HANDLE pHandle;
   LPVOID remoteString;
   LPVOID remoteLoadLib;

   pHandle = OpenProcess(PROCESS_ALL_ACCESS, FALSE, pID);

   if(!pHandle)
      return false;

   remoteLoadLib = (LPVOID)GetProcAddress(GetModuleHandle(L"kernel32.dll"), "LoadLibraryA");

   remoteString = (LPVOID)VirtualAllocEx(pHandle, NULL, strlen(DLL_PATH), MEM_RESERVE|MEM_COMMIT, PAGE_READWRITE);
   WriteProcessMemory(pHandle, (LPVOID)remoteString, dllpath, strlen(dllpath), NULL);
   if (NULL == CreateRemoteThread(pHandle, NULL, NULL, (LPTHREAD_START_ROUTINE)remoteLoadLib, (LPVOID)remoteString, NULL, NULL)) {
	   return false;
   }
   CloseHandle(pHandle);

   return true;
}

