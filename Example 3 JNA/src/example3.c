// Projeto : JNA Example #3
// Algoritmo : Example3.c
// Função : Send a Struct to C (By Reference)
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Sáb, Mai  9 2015 23:13:24 BRT

#include <stdio.h>

typedef struct Example3Struct {
	int val;
} Example3Struct;

void example3_sendStruct(const Example3Struct* sval)
{
	// note: printfs called from C won't be flushed
	// to stdout until the Java process completes
	printf("(C) %d\n", sval->val);
}
