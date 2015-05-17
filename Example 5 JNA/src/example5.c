// Projeto : JNA Example #5
// Algoritmo : Example5.c
// Função : Modify Struct in C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Dom, Mai 10 2015 13:53:47 BRT

typedef struct Example5Struct {
	int val;
} Example5Struct;

void example5_fillStruct(Example5Struct* pval)
{
	pval->val = 32;
}
