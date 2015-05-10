// JNA Example #6: Send an Array of Structs to C

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
					
				


