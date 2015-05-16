// Projeto : JNA Example #13
// Algoritmo: example13.c
// Função : : Send a Struct Containing an Array of Strings to C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 12:40:00 BRT 2015

#include <stdio.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef struct Example13Struct {
	int numVals;
	char** vals;
} Example13Struct;

void example13_sendStrings(const Example13Struct* pVal)
{
	int loop = 0;
	printf("(C) Example 13: %d strings:\n", pVal->numVals);
	for (loop = 0; loop<pVal->numVals; loop++)
	{
		printf("\t(C): %s\n", pVal->vals[loop]);
	}
}
// Fim do algoritmo
