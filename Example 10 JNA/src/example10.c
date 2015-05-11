// Projeto : JNA Example #10
// Algoritmo: example10.c
// Função : : Send a Struct Containing an Array of Doubles to C
// Autor(es) : Hugo Dionizio Santos
// Data : Seg Mai 11 11:46:18 BRT 2015

#include <stddef.h>
#include <assert.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef struct Example10Struct {
	int numVals;
	double* vals;
} Example10Struct;

double example10_sendStruct(const Example10Struct* pval)
{
	int loop = 0;
	double total = 0.0;
	assert(NULL != pval);
	if (0 == pval->numVals)
	{
		return 0;
	}
	assert(NULL != pval->vals);
	for (loop=0; loop<pval->numVals; loop++)
	{
		total += pval->vals[loop];
	}
	return total;
}
// Fim do algoritmo
