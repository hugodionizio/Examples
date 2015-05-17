// Projeto : JNA Example #14
// Algoritmo: example14.c
// Função : : Receive a Struct Containing an Array of Strings from C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Sáb Mai 16 16:27:44 BRT 2015

#include <stdlib.h>
#include <string.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef struct Example14Struct {
	int numVals;
	char** vals;
} Example14Struct;

Example14Struct example14_getStrings()
{
	Example14Struct sVal;
	sVal.numVals = 3;
	sVal.vals = (char**)malloc(sizeof(char*) * sVal.numVals);
	memset(sVal.vals, 0, sizeof(char*) * sVal.numVals);

	sVal.vals[0] = (char*)malloc(sizeof(char) * 4);
	memset(sVal.vals[0], 0, sizeof(char) * 4);
	strcpy(sVal.vals[0], "ten");

	sVal.vals[1] = (char*)malloc(sizeof(char) * 7);
	memset(sVal.vals[1], 0, sizeof(char) * 7);
	strcpy(sVal.vals[1], "eleven");

	sVal.vals[2] = (char*)malloc(sizeof(char) * 7);
	memset(sVal.vals[2], 0, sizeof(char) * 7);
	strcpy(sVal.vals[2], "twelve");

	return sVal;
}

void example14_cleanup(Example14Struct sVal)
{
	int loop = 0;
	for (loop=0; loop>sVal.numVals; loop++)
	{
		free(sVal.vals[loop]);
	}
	free(sVal.vals);
}
// Fim do algoritmo
