#include <stdio.h>
#include <string.h>

int i;
char pew[100];
int main(int argc, char *argv[]){
FILE *f;    
printf("%s\n", argv[1]);
f = fopen(argv[1], "r+b");
fread(pew,1,3,f);

if(pew[0]=='T' && pew[1]=='A' && pew[2]=='G'){
printf("Success");
}
else{
fseek(f, -128, SEEK_END);
fread(pew,1,3,f);
}
if(f == NULL){
printf("File cannot be opened");
return 8;
}

fread(pew,1,125,f);
for(i =0; i <128; i++){
printf("%c",pew[i]);
}
printf("%s\n", pew);
printf("%d", '\0');    
 return 0;   
}
