// Projeto : JNA Example #17
// Algoritmo: example17.c
// Função : : Send a ragged multidimensional array of strings to C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 16:27:45 BRT 2015

#include <stdio.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef struct Example17StructA {
	int numVals;
	char** vals;
} Example17StructA;

typedef struct Example17StructB {
	int numVals;
	Example17StructA* vals;
} Example17StructB;

void example17_sendMultidimensionalArray(const Example17StructB* pVal)
{
	int loop1 = 0;
	int loop2 = 0;
	printf("(C) Example 17: received %d outer array values\n", pVal->numVals);
	for (loop1=0; loop1<pVal->numVals; loop1++)
	{
		printf("\t(C) outer array %d contains %d inner items\n", loop1, pVal->vals[loop1].numVals);
		for (loop2=0; loop2<pVal->vals[loop1].numVals; loop2++)
			{
				printf("\t\t(C)  item [%d][%d]: %s\n", loop1, loop2, pVal->vals[loop1].vals[loop2]);
			}
	}
}

// Fim do algoritmo
