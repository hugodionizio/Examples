// Projeto : JNA Example #19
// Algoritmo: example19.c
// Função : : Get a union from C
// Autor(es) : Hugo Dionizio Santos
// Data : Sáb Mai 16 16:27:45 BRT 2015

// Seção de Variáveis Globais

// Interface do algoritmo

// Função Principal
//	Seção de Variáveis Locais
//	Seção de Comandos

// Seção de Funções
typedef union Example19Union {
	int intnumber;
	float floatnumber;
	char* stringval;
} Example19Union;

enum Example19UnionType{
    EX19_UNKNOWN = 0,
    EX19_INTEGER = 1,
    EX19_FLOAT = 2,
    EX19_STRING = 3
};

typedef struct Example19UnionHolder {
	enum Example19UnionType uniontype;
	Example19Union unionval;
} Example19UnionHolder;

Example19UnionHolder example19_getUnion()
{
	Example19UnionHolder holder;
	holder.uniontype = EX19_INTEGER;
	holder.unionval.intnumber = 26;
	return holder;
}

// Fim do algoritmo
