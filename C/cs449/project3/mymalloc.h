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
void *my_firstfit_malloc(int);
void my_free(void *);
void print_memory();
struct memory_node *first_search(int);
struct memory_node *split_node(void *, int);
/* ---------------------*/


/* Will check the current node's previous and next nodes and adjust accordingly if they are free. */ 
void coalesce_nodes(struct memory_node *empty_node){
	struct memory_node *prev = empty_node->previous;
	struct memory_node *nex = empty_node->next;
	if(start == empty_node){
		if(nex->used == 0){	
		empty_node->size_chunk += sizeof(struct memory_node)+ nex->size_chunk;
		empty_node->next = nex->next;
		empty_node->next->previous = empty_node;
		}
	return;
	}
	else if(prev->used == 0 && nex->used == 0){
		prev->size_chunk += 2*sizeof(struct memory_node)+empty_node->size_chunk + nex->size_chunk;
		prev->next = nex->next;
		prev->next->previous = prev;
	}
	else if(prev->used == 0 ){
		prev->size_chunk += sizeof(struct memory_node)+ empty_node->size_chunk;
		prev->next = empty_node->next;
		prev->next->previous = prev;
	}
	else if(nex-> used == 0){
		empty_node->size_chunk += sizeof(struct memory_node)+ nex->size_chunk;
		empty_node->next = nex->next;
		empty_node->next->previous = empty_node;
	}
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

/* Starts from the beginning of the node and looks for
 a free space that is large enough to accomodate the size. */
struct memory_node *split_node(void * start_of_node, int x){
	struct memory_node *temp = start_of_node;
	if(temp->size_chunk > sizeof(struct memory_node)+1){
		void *new_node= start_of_node + sizeof(struct memory_node)+x;
		struct memory_node *new_struct = new_node;
		new_struct->next = temp->next;
		temp->next = new_struct;
		temp->next->previous = temp;
		temp->used = 1;
		temp->next->size_chunk = temp->size_chunk - x - sizeof(struct memory_node);
		temp->size_chunk = x;
		temp->next->used = 0;
		if(temp->next->next != NULL)
		{
		temp->next->next->previous = temp->next;
		}
		return temp;
	}
	else{
		return temp;
	}
}

struct memory_node * first_search(int x){
	struct memory_node *temp = start;
	while(7){
		if(temp == NULL){
			break;
		}
		else if(temp->used == 0 && temp->size_chunk > x){
			temp= split_node(temp,x);
			return temp;
		}
		else if(temp->used == 0 && temp->size_chunk == x){
			temp->used == 1;
			return temp;
		}
		temp = temp->next;
	}
	
	temp = sbrk(sizeof(struct memory_node)+ x);
	temp->used = 1;
	temp->size_chunk = x;	
	add_node(temp);
	return temp;
}
void print_memory(){
	struct memory_node *t = start;
	printf("Start :");
	while(t != end && start != NULL){
	printf("{Used: %d, Size: %d}-->", t->used, t->size_chunk);
	t = t->next;
	}
	if(start!= NULL)
	printf("{Used: %d, Size: %d}", t->used, t->size_chunk);
	printf(": End\n");
}

void *my_firstfit_malloc(int x){
	struct memory_node *t;
	t = first_search(x);
	 return (t+1);
}
void my_free(void *x){
	struct memory_node *release;
	release = x-sizeof(struct memory_node);
	if(end == release && end != start){
		do{
			end = end->previous;
			end->next = 0;
			sbrk(0-(sizeof(struct memory_node)+release->size_chunk));
		}
		while(end->used == 0 && end != NULL);
	}
	else if(end == release && end == start){
		end = 0;
		start = 0;
		sbrk(0-(sizeof(struct memory_node)+release->size_chunk));

	}
	else{	
		release->used = 0;
		coalesce_nodes(release);
	}
	release = 0;
}
