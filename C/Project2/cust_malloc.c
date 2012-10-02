#include <stdio.h>
#include <unistd.h>

struct memory_node{
	char used;
	int size_chunk;
	struct memory_node *next;
	struct memory_node *previous;
};

/* Start and end nodes of the chain. */
struct memory_node *start;
struct memory_node *end;	
/* ---------------------------------- */

/* Function definitions */
void *cust_malloc(int);
void cust_free(void *);
void print_memory();

/* ---------------------*/


int main(){
	int *x;
	int *y;
	x = cust_malloc(sizeof(int));
	y = cust_malloc(sizeof(int));
	*x = 4;
	*y = 6;	
	printf("%d\n", *x);
	printf("%d\n", *y);
	print_memory();
	cust_free(y);
	cust_free(x);

}
/* Adds the node in the parameter to the end of the list.
Also sets the end to this node. */
void add_node(struct memory_node *curr_node){
	struct memory_node *temp = start;
	if(start == 0){
		start = curr_node;
	}
	else{
		while(temp->next != 0){
			temp = temp->next;
		}
		temp->next = curr_node;
		temp->next->previous = temp;
	}
	end = curr_node;
}

void print_memory(){
	struct memory_node *t = start;
	printf("Start :");
	while(t != end){
	printf("{Used: %d, Size: %d}-->", t->used, t->size_chunk);
	t = t->next;
	}
	printf("{Used: %d, Size: %d}", t->used, t->size_chunk);
	printf(": End\n");
}

void *cust_malloc(int x){
	struct memory_node *t;

	 t = sbrk(sizeof(struct memory_node)+ x);
	 t->used = 1;
	 t->size_chunk = x;
	 add_node(t);
	 return (t+1);
}
void cust_free(void *x){
	struct memory_node *release;
	release = x-sizeof(struct memory_node);
	sbrk(0-(sizeof(struct memory_node)+release->size_chunk));
}
