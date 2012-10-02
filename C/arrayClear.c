#include<stdio.h>

void blank_array(int *array, int length){
    while(length > 0){
        *array = 0;
        array++;
        length--;
    }
}

int rawr[] = {6, 4 ,2 , 65};
int main(){
    blank_array(rawr, 4);
    printf("%d", rawr[3]);
    return 0;
}
