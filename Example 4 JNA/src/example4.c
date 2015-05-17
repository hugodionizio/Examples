// Projeto : JNA Example #4
// Algoritmo : Example4.c
// Função : Get a Struct from C (By Value)
// Autor(es) : Hugo Dionizio Santos (adaptações e correções), Ethan Shayne in http://www.eshayne.com/jnaex
// Data : Dom, Mai 10 2015 03:28:33 BRT
					
typedef struct Example4Struct {
	int val;
} Example4Struct;

Example4Struct example4_getStruct()
{
	Example4Struct sval;
	sval.val = 23;
	return sval;
}
					
				


