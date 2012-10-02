#include <stdio.h>
#include "mymalloc.h"
#define cust_malloc my_firstfit_malloc
#define cust_free my_free
int main(){
int *a, *b, *c, *d, *f, *g, *h, *i, *j, *k, *l, *m;
printf("Adding 10 nodes..\n");
a = cust_malloc(sizeof(int));
b = cust_malloc(sizeof(int));
c = cust_malloc(sizeof(int));
d = cust_malloc(sizeof(int));
f = cust_malloc(sizeof(int));
g = cust_malloc(sizeof(int));
h = cust_malloc(sizeof(int));
i = cust_malloc(sizeof(int));
j = cust_malloc(sizeof(int));
k = cust_malloc(sizeof(int));
print_memory();
printf("\nClearing a node before the end and then removing end, for 8 nodes left..\n");
cust_free(j);
cust_free(k);
print_memory();
printf("\nClearing Start Node:\n");
cust_free(a);
print_memory();
printf("\nClearing Node after Start, Coalescing...\n");
cust_free(b);
print_memory();
printf("\nAdding Node with first search (Should be Start and splitting it).\n");
b=cust_malloc(sizeof(int));
print_memory();

printf("\n removing 3 middle nodes(closest to the end) to test 3 coalescing \n");
cust_free(f);
print_memory();
printf("\n");
cust_free(h);
print_memory();
printf("\n");
cust_free(g);
print_memory();

printf("\n Removing 3rd Node to show forward Coalescing..\n");
cust_free(c);
print_memory();
printf("\n Removing nodes one at a time...\n");
cust_free(d);
print_memory();
printf("\n");
cust_free(i);
print_memory();
printf("\n");
cust_free(b);
print_memory();
return 0;
}
