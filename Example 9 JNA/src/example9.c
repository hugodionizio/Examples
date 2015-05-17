// Projeto : JNA Example #9
// Algoritmo: example9.c
// Função : Receive an Array of Doubles from C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Seg Mai 11 11:05:54 BRT 2015

#include <stdlib.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
void example9_getDoubleArray(double** ppVals, int* pNumVals)
{
	int loop = 0;
	*pNumVals = 100;
	*ppVals = (double*)malloc(sizeof(double) * *pNumVals);
	memset(*ppVals, 0, sizeof(double) * *pNumVals);
	for (loop=0; loop<*pNumVals; loop++)
	{
		// populate the array with junk data (just for the sake of the example)
		(*ppVals)[loop] = ((double)loop + 100) / 100;
	}
}

void example9_cleanup(double* pVals)
{
	free(pVals);
}
					
				


// Fim do algoritmo
