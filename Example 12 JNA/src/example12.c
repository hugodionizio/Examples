// Projeto : JNA Example #12
// Algoritmo: example12.c
// Função : : Retrieve a Struct Containing an String from C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 09:34:01 BRT 2015

#include <stdlib.h>
#include <string.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef struct Example12Struct {
	char* pszVal;
} Example12Struct;

Example12Struct example12_getString()
{
	Example12Struct sVal;
	sVal.pszVal = (char*)malloc(sizeof(char) * 6);
	memset(sVal.pszVal, 0, sizeof(char) * 6);
	strcpy(sVal.pszVal, "hello");
	return sVal;
}
void example12_cleanup(Example12Struct sVal)
{
	free(sVal.pszVal);
}

// Fim do algoritmo
