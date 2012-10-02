
void stringCopy(char *dest, char *source){
    while(1){
     *dest = *source;

     if(*dest == '\0')
        return;

    dest++;
    source++;

    }

}
