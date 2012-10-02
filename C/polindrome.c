#include <stdio.h>
#include <string.h>

char input[100];
int input_length;
int count;

int main(){
scanf("%s", input);
input_length = strlen(input)-1;
for(count = 0; count <=input_length; count++){
    if(input[count]!= input[input_length]){
    break;
    }
    else{
    input_length--;
    }
}
if(input_length <= count){
    printf("It Is");
}

}
