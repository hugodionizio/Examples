// Projeto : JNA Example #15
// Algoritmo: example15.c
// Função : : Send a Struct Containing an Array of Structs to C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 16:27:45 BRT 2015

#include <stdio.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef struct Example15StructA {
	int val;
} Example15StructA;

typedef struct Example15StructB {
	int numAs;
	Example15StructA* as;
} Example15StructB;

void example15_send(const Example15StructB* pVal)
{
	int loop = 0;
	printf("(C) Example 15: %d values\n", pVal->numAs);
	for (loop=0; loop<pVal->numAs; loop++)
	{
		printf("\t(C): %d\n", pVal->as[loop].val);
	}
}

// Fim do algoritmo
