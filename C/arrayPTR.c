#include <stdio.h>

int array[] = {4 , 5, 77, 54, 43 , 0 , 11};
int *array_ptr;
int main(){
    array_ptr = array;
    while(*array_ptr != 0){
        ++array_ptr;
    }

    printf("The number of elements before 0, is %d", (array_ptr-array));
    return 0;
}
