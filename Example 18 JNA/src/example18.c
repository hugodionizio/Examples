// Projeto : JNA Example #18
// Algoritmo: example18.c
// Função : : Send a union to C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 16:27:45 BRT 2015

#include <stdio.h>

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef union Example18Union {
	int intnumber;
	float floatnumber;
	char* stringval;
} Example18Union;

enum Example18UnionType{
    EX18_UNKNOWN = 0,
    EX18_INTEGER = 1,
    EX18_FLOAT = 2,
    EX18_STRING = 3
};

typedef struct Example18UnionHolder {
	enum Example18UnionType uniontype;
	Example18Union unionval;
} Example18UnionHolder;

void example18_sendUnion(const Example18UnionHolder* pHolder)
{
	switch (pHolder->uniontype)
	{
		case EX18_INTEGER:
		{
			printf("(C) Example 18: received integer value: %d\n", pHolder->unionval.intnumber);
			break;
		}
		case EX18_FLOAT:
		{
			printf("(C) Example 18: received float value: %f\n", pHolder->unionval.floatnumber);
			break;
		}
		case EX18_STRING:
		{
			printf("(C) Example 18: received string value: %s\n", pHolder->unionval.stringval);
			break;
		}
		default:
		{
			printf("(C) Example 18: ERROR: unrecognized type %d\n", pHolder->uniontype);
			break;
		}
	}
}

// Fim do algoritmo
