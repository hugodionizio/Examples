// Projeto : JNA Example #8
// Algoritmo: example8.c
// Função : Send an Array of Doubles to C
// Autor(es) : Hugo Dionizio Santos
// Data : Seg Mai 11 09:58:36 BRT 2015

// Seção de Variáveis Globais



// Interface do algoritmo


// Função Principal

//Seção de Variáveis Locais

//Seção de Comandos



// Seção de Funções
double example8_sendDoubleArray(const double* vals, int numVals)
{
	int loop = 0;
	double total = 0;
	for (loop=0; loop<numVals; loop++)
	{
		total += vals[loop];
	}
	return total;
}
					
				


// Fim do algoritmo
