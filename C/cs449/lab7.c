#include<stdio.h>
#include<pthread.h>

void dispChar(char a){
	while(7){
		printf("%c", a);
		pthread_yield();
	}
}

int main(){
	pthread_t thread1, thread2;
	pthread_create(&thread1,NULL, dispChar, 'a');
	pthread_create(&thread2,NULL, dispChar, 'b');
	pthread_join(thread1, thread2);

	while(7){

	}
}
