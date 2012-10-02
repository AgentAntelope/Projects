/* Author Sean Myers */

#include <stdio.h>
#include <string.h>

int i;
char begin[3];
char end[3];

struct id3{
 char tag[3];
 char title[30];
 char artist[30];
 char album[30];
 char year[4];
 char comment[28];
 char separator;
 char track_num;
 char genre;
};

struct id3 tag_editor;

/* Will edit the content based on what is in the array.
It works by checking the string for a certain sub string and takes the next arg to put in. 
*/
/* Potential pitfalls: Putting in an arg like: -title hello world instead of -title "hello 
world" will make the program send an output that you put in the wrong input. */
void edit_tag(int argsLength, char *allArgs[]){
    char *selected;
    char *argSelection;
    int count = 2;
    int length;
    while(count < argsLength){
        if((selected=strstr(allArgs[count],"title")) != NULL){
            count++;
            strncpy(tag_editor.title, allArgs[count], 30);
        }
        else if((selected=strstr(allArgs[count],"artist")) != NULL){
            count++;
            strncpy(tag_editor.artist, allArgs[count], 30);
        }
        else if((selected=strstr(allArgs[count],"album")) != NULL){
            count++;
            strncpy(tag_editor.album, allArgs[count], 30);
        }
        else if((selected=strstr(allArgs[count],"track number")) != NULL){
            count++;
            tag_editor.track_num = atoi(allArgs[count]);
        }
        else if((selected=strstr(allArgs[count],"track")) != NULL){
            count += 2;
            tag_editor.track_num = atoi(allArgs[count]);
        }
        else if((selected=strstr(allArgs[count],"year")) != NULL){
            count++;
            strncpy(tag_editor.year, allArgs[count], 4);
        }
        else if((selected=strstr(allArgs[count],"comment")) != NULL){
            count++;
            strncpy(tag_editor.comment, allArgs[count], 28);
        }
        else{
            printf("There was something wrong with your input, please review it and try again");
        }
        count++;

    }
}
/* Prints the a single data type of the struct without relying on a string*/
void print_charArray(char *c_array, int length){
    int i;
    for(i = 0; i < length; i++){
        printf("%c", c_array[i]);
        }
    printf("\n");
    }

int main(int argc, char *argv[]){
  FILE *f;
  f = fopen(argv[1], "r+b");
  if(f == NULL){
    printf("File cannot be opened");
    return 8;
   }

  fread(begin,1,3,f);
  fseek(f, -128, SEEK_END);
  fread(end,1,3,f);


/*This area will determine if there is TAG and handle appropriately. */
  if(begin[0]=='T' && begin[1]=='A' && begin[2]=='G'){
      fseek(f, 0, SEEK_SET);
      fread(&tag_editor, 1,128, f);
      if(argc > 2){
       edit_tag(argc , argv);
      }
      fseek(f,0,SEEK_SET);
      }

  else if(end[0]=='T' && end[1]=='A' && end[2]=='G'){
      fseek(f, -128, SEEK_END);
      fread(&tag_editor, 1,128, f);
      if(argc > 2){
          edit_tag(argc , argv);
      }
      fseek(f,-128, SEEK_END);
  }
  else if(argc > 2){
      fseek(f, 0, SEEK_END);
      strncpy(tag_editor.tag, "TAG", 3);
    //  tag_editor.genre = NULL;
    //  tag_editor.separator = NULL;
      edit_tag(argc , argv);
  }
  else{
   printf("There was no ID3 Tag and you did not put a put any values to add, so no ID3 Tag was appended");
   return 0;
  }
  /*Prints the current contents after all the updates */

        printf("Title: ");
        print_charArray(tag_editor.title,30);
        printf("Artist: ");
        print_charArray(tag_editor.artist,30);
        printf("Album: ");
        print_charArray(tag_editor.album,30);
				printf("Year: ");
				print_charArray(tag_editor.year, 4);
        printf("Track Number: ");
        printf("%d", tag_editor.track_num);
        printf("\n");
        printf("Comment: ");
        print_charArray(tag_editor.comment, 28);
/* Writes to the modified file */
        fwrite(&tag_editor, 1, 128, f);
  return 0;
}


