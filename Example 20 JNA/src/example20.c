// Projeto : JNA Example #20
// Algoritmo: example20.c
// Função : : Send an array of unions to C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Sáb Mai 16 16:27:45 BRT 2015

#include <stdio.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef union Example20Union {
	int intnumber;
	float floatnumber;
	char* stringval;
} Example20Union;

enum Example20UnionType{
    EX20_UNKNOWN = 0,
    EX20_INTEGER = 1,
    EX20_FLOAT = 2,
    EX20_STRING = 3
};

typedef struct Example20UnionHolder {
	enum Example20UnionType uniontype;
	Example20Union unionval;
} Example20UnionHolder;

typedef struct Example20UnionList {
	int numVals;
	Example20UnionHolder* vals;
} Example20UnionList;

void example20_send(const Example20UnionList* pVal)
{
	int loop = 0;
	printf("(C) Example 20: %d values\n", pVal->numVals);
	for (loop=0; loop<pVal->numVals; loop++)
	{
		switch (pVal->vals[loop].uniontype)
		{
			case EX20_INTEGER:
			{
				printf("(C) Example 20: received integer value: %d\n", pVal->vals[loop].unionval.intnumber);
				break;
			}
			case EX20_FLOAT:
			{
				printf("(C) Example 20: received float value: %f\n", pVal->vals[loop].unionval.floatnumber);
				break;
			}
			case EX20_STRING:
			{
				printf("(C) Example 20: received string value: %s\n", pVal->vals[loop].unionval.stringval);
				break;
			}
			default:
			{
				printf("(C) Example 20: ERROR: unrecognized type %d\n", pVal->vals[loop].uniontype);
				break;
			}
		}
	}
}

// Fim do algoritmo
