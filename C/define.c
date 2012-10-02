#include <stdio.h>
#define ABS(a)(a<0)?(0-a):a

int main(){
    int a = -9;
    a=ABS(a);
    printf("%d\n", a);
    return 0;
}
