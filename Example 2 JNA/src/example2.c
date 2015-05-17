// Projeto : JNA Example #2
// Algoritmo : Example2.c
// Função : Send a String to C, Receive a String from C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Sáb, Mai  9 2015 08:55:37 BRT

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
					
void example2_sendString(const char* pszVal)
{
	// note: printfs called from C won't be
	// flushed to stdout until the Java
	// process completes
	printf("(C) '%s'\n", pszVal);
}

void example2_getString(char** ppszVal)
{
	*ppszVal = (char*)malloc(sizeof(char) * 6);
	memset(*ppszVal, 0, sizeof(char) * 6);
	strcpy(*ppszVal, "hello");
}

void example2_cleanup(char* pszVal)
{
	free(pszVal);
}
					
				


