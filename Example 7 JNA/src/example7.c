// Projeto : JNA Example #7
// Algoritmo : example7.c
// Função : Retrieve an Array of Structs from C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Dom 10 Mai 2015 20:45:07 BRT

#include <stdio.h>
#include <stdlib.h>

// Seção de Variáveis Globais
typedef struct Example7Struct {
	int val;
} Example7Struct;

// Interface do algoritmo

// Função Principal

// Seção de Funções
void example7_getStructArray(Example7Struct** ppVals, int* pNumVals)
{
	*pNumVals = 3;
	*ppVals = (Example7Struct*)malloc(sizeof(Example7Struct) * 3);
	memset(*ppVals, 0, sizeof(Example7Struct) * 3);
	(*ppVals)[0].val = 1;
	(*ppVals)[1].val = 2;
	(*ppVals)[2].val = 3;
}

void example7_cleanupStructArray(Example7Struct* pMem)
{
	printf("(C) cleaning up memory...\n");
	free(pMem);
}

// Fim do algoritmo
