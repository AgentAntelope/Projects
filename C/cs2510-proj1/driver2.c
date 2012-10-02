#include "locking.h"
#include <stdio.h>
#include <unistd.h>
int main(){
	struct node * a = (struct node *) malloc(sizeof(struct node));
	struct node * b = (struct node *) malloc(sizeof(struct node));
	a->value = 10;
	b->value = 30;
	struct node * ptr = a;
	compare_and_swap_ptr(&ptr, a, b);
	printf("Should work: %d\n", ptr->value);
}
