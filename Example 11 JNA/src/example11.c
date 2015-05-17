// Projeto : JNA Example #11
// Algoritmo: example11.c
// Função : : Retrieve a Struct Containing an Array of Doubles from C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Seg Mai 11 15:29:20 BRT 2015

#include <stdlib.h>
#include <string.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef struct Example11Struct {
	int numVals;
	double* vals;
} Example11Struct;

Example11Struct example11_getStruct()
{
	int loop=0;
	Example11Struct sVal;
	sVal.numVals = 100;
	sVal.vals = (double*)malloc(sizeof(double) * sVal.numVals);
	memset(sVal.vals, 0, sizeof(double) * sVal.numVals);
	for (loop=0; loop<sVal.numVals; loop++)
	{
		sVal.vals[loop] = ((double)loop + 100) / 100;
	}
	return sVal;
}

void example11_cleanup(double* pVals)
{
	free(pVals);
}

// Fim do algoritmo
