#include <stdio.h>
#include <string.h>
char input[30];
int comp_wins = 0;
int player_wins = 0;

void init();
void check_win(int, char *);
int main(){
    srand((unsigned int)time(NULL));
    printf("Welcome to Rock, Paper, Scissors\n\n");
    printf("Would you like to play? ");
    scanf("%s", input);
    if(!strncmp(input, "yes",3 )){
        for(;comp_wins < 3 && player_wins < 3;)
        init();
    }
    return 0;
}

void init(){

    int comp_choice;
    int winner;
    char player_choice[20];

    printf("What is your choice? ");
    scanf("%s", player_choice);

    comp_choice = rand() % 3;
    check_win(comp_choice, player_choice);
    printf("The score is now you: %d , computer: %d \n" , player_wins, comp_wins);

}

void check_win(int cmp_choice, char play_choice[]){
    int player_num;
    char cmp_choice_name[12];
/* Assign the player a number based off his string */
    if(!strcmp(play_choice, "scissors")){
        player_num = 0;
    }
    else if(!strcmp(play_choice, "rock")){
        player_num = 1;
    }
    else{
        player_num = 2;
    }

/* Assign the computer a value to be displayed later*/
    if(cmp_choice == 0){
        strcpy(cmp_choice_name, "scissors");
    }
    else if(cmp_choice == 1){
       strcpy(cmp_choice_name,"rock");
   }
    else{
        strcpy(cmp_choice_name, "paper");
    }


 /* See who won*/
 printf("The computer chose %s.", cmp_choice_name);
    if(player_num == cmp_choice){
        printf(" It is a tie, no one wins this round \n");
            }
    else if(player_num-cmp_choice == 1 || player_num - cmp_choice == -2){
        printf(" You won!\n");
        player_wins++;
    }
    else{
        printf(" You lost! \n");
        comp_wins++;
    }
}
