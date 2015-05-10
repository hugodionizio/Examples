// JNA Example #5: Modify Struct in C

typedef struct Example5Struct {
	int val;
} Example5Struct;

void example5_fillStruct(Example5Struct* pval)
{
	pval->val = 32;
}
