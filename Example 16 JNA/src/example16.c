// Projeto : JNA Example #16
// Algoritmo: example16.c
// Função : : Receive a Struct Containing an Array of Structs from C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 16:27:45 BRT 2015

#include <stdlib.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef struct Example16StructA {
	int val;
} Example16StructA;

typedef struct Example16StructB {
	int numAs;
	Example16StructA* as;
} Example16StructB;

Example16StructB example16_get()
{
	int loop = 0;
	Example16StructB sVal;
	sVal.numAs = 3;
	sVal.as = (Example16StructA*)malloc(sizeof(Example16StructA) * sVal.numAs);
	memset(sVal.as, 0, sizeof(Example16StructA) * sVal.numAs);
	for (loop=0; loop<sVal.numAs; loop++)
	{
		// fill in dummy data for the purpose of this example
		sVal.as[loop].val = loop;
	}
	return sVal;
}

void example16_cleanup(Example16StructB sVal)
{
	free(sVal.as);
}
// Fim do algoritmo
