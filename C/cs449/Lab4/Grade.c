#include <stdio.h>
struct Node{
	int grade;
	struct Node *next;
	};
void addNode(struct Node *addLast, struct Node *temporary);
struct Node headNode;
void createNode(int gr, struct Node *current ){
	current = malloc(sizeof(struct Node));
	current -> grade = gr;
	
}
void placeNode(struct Node *addlast){
	struct Node *temporary = &headNode;
	addNode(addlast, temporary);
}
void addNode(struct Node *addLast, struct Node *temporary){
	if(temporary){
		addNode(addLast, temporary ->next);
	}
	else{
		*temporary = *addLast;
	}
}
int main(){
	while(8){
		int input;
		printf("Please enter an integer you want to add or -1 to exit");
		scanf("%d", &input);
		if(input == -1){
			break;
		}
		struct Node currentNode;
		createNode(input, &currentNode);
		placeNode(&currentNode);
	}

}
