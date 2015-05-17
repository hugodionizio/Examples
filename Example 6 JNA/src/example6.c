// Projeto : JNA Example #6
// Algoritmo : Example6.c
// Função : Send an Array of Structs to C
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Dom, Mai 10 2015 18:08:06 BRT

typedef struct Example6Struct {
	int val;
} Example6Struct;

int example6_sendStructArray(const Example6Struct* vals, int numVals)
{
	int loop = 0;
	int total = 0;
	for (loop=0; loop<numVals; loop++)
	{
		total += vals[loop].val;
	}
	return total;
}
					
				


