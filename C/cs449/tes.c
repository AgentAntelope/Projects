#include<stdio.h>
FILE *pew;
int main(){
char buf[100];
pew = fopen("/dev/card", "r");
    fread(buf, 1,1,pew);
    printf("%d", buf[0]);
    return 0;
}
