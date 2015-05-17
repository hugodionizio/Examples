// Projeto : JNA Example #21
// Algoritmo: example21.c
// Função : : Receive an array of unions from C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Sáb Mai 16 16:27:45 BRT 2015

#include <stdlib.h>
#include <string.h>
#include <assert.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef union Example21Union {
	int intnumber;
	float floatnumber;
	char* stringval;
} Example21Union;

enum Example21UnionType{
    EX21_UNKNOWN = 0,
    EX21_INTEGER = 1,
    EX21_FLOAT = 2,
    EX21_STRING = 3
};

typedef struct Example21UnionHolder {
	enum Example21UnionType uniontype;
	Example21Union unionval;
} Example21UnionHolder;

typedef struct Example21UnionList {
	int numVals;
	Example21UnionHolder* vals;
} Example21UnionList;

Example21UnionList example21_get()
{
	Example21UnionList list;
	list.numVals = 3;
	list.vals = (Example21UnionHolder*)malloc(sizeof(Example21UnionHolder) * 3);
	memset(list.vals, 0, sizeof(Example21UnionHolder) * 3);

	list.vals[0].uniontype = EX21_INTEGER;
	list.vals[0].unionval.intnumber = 33;
	list.vals[1].uniontype = EX21_FLOAT;
	list.vals[1].unionval.floatnumber = 34.5f;
	list.vals[2].uniontype = EX21_STRING;
	list.vals[2].unionval.stringval = (char*)malloc(sizeof(char) * 6);
	strcpy(list.vals[2].unionval.stringval, "hello");

	return list;
}

void example21_cleanup(Example21UnionList list)
{
	int i=0;

	if (0 == list.numVals)
	{
		assert(NULL == list.vals);
		return;
	}
	assert(NULL != list.vals);

	for (i=0; i<list.numVals; i++)
	{
		if (EX21_STRING == list.vals[i].uniontype)
		{
			assert(NULL != list.vals[i].unionval.stringval);
			free(list.vals[i].unionval.stringval);
		}
	}
	free(list.vals);
}

// Fim do algoritmo
