// Example #4: Get a Struct from C (By Value)
					
typedef struct Example4Struct {
	int val;
} Example4Struct;

Example4Struct example4_getStruct()
{
	Example4Struct sval;
	sval.val = 23;
	return sval;
}
					
				


