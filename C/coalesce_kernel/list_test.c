#include <stdio.h>
#include <stdlib.h>
#include "list.h"
#define FREE 0
#define ALLOCATED 1

struct allocation_node {
	char status;
	int size;
	int page_addr;
	struct list_head list;
};

int main(){
	struct list_head start_of_list;
	INIT_LIST_HEAD(&start_of_list);
	struct allocation_node start;
	start.status = FREE;
	start.size = 20000000;
	start.page_addr = 0x10000000;
	INIT_LIST_HEAD(&(start.list));
	//list_add(&(start.list),&start_of_list);
	printf("0x%x\n", start.page_addr);
	int next = allocate(&start_of_list, 10);
	int nnext = allocate(&start_of_list, 10);
	printf("0x%x\n", next);
	printf("0x%x\n", nnext);
	return 0;
}


void free_address(struct list_head * head_list, int page){
	struct allocation_node * cur, * found;
	list_for_each_entry(cur ,head_list, list){
		if(cur->page_addr == page){
			found = cur;
		}	
	}
	if(found == NULL){
		return;
	}

	//Set the clear values.
	found->status = FREE;

	//Coalesce nodes.
	struct allocation_node * next = list_entry(found->list.next, struct allocation_node, list); 
	struct allocation_node * prev = list_entry(found->list.prev, struct allocation_node, list); 

	if(next->page_addr != page && next->status == FREE){
		list_del(found->list.next);
		found->size += next->size;
		free(next);
	}
	if(prev->page_addr != page && prev->status == FREE){
		list_del(found->list.prev);
		found->size += prev->size;
		free(prev);	
	}
	
}

int allocate(struct list_head * head_list, int size){
	struct allocation_node * used_node, *cur, *node_to_consume, *new_node;
	node_to_consume = NULL;
	list_for_each_entry(cur, head_list, list){
		if(cur->status == FREE && cur->size >= size){
			node_to_consume = cur;	
			break;
		}
	}
	if(node_to_consume == NULL){
		exit(EXIT_FAILURE);
	}
	
	node_to_consume->status = ALLOCATED;
	if(node_to_consume->size == size){
		return node_to_consume->page_addr;
	}

	int current_size = node_to_consume->size;
	current_size -= size;
	new_node = (struct allocation_node*)malloc(sizeof(struct allocation_node));
	INIT_LIST_HEAD(&(new_node->list));
	list_add(&(new_node->list), &(node_to_consume->list));
	new_node->size = current_size;
	new_node->page_addr = node_to_consume->page_addr + size;
	
	node_to_consume->size = size;
	return node_to_consume->page_addr;
}


