#include<stdio.h>
FILE *cardDeck;

int cardDraw(int *aces){
    char card[1];
	int cardValue;
	fread(card, 1, 1, cardDeck);
	printf("Card: %d", card[0]);
	card[0] = (card[0] % 13);
	switch(card[0]){
	case 0:
		*aces = *aces + 1;
		cardValue = 11;
		break;
	case 10:
		cardValue = 10;
		break;
	case 11:
		cardValue = 10;
		break;
	case 12:
		cardValue = 10;
		break;
	default:
		cardValue = card[0];
		break;
	}
	return cardValue;
}
int changeAce(int *total, int *aceTotal){
	int newTotal = *total + 11;
	if(newTotal > 21){
		*aceTotal = *aceTotal - 1;
		return 1;
	}
	else
		return 11;
}

int checkTotal(int *total, int *aceTotal){
	if(*total > 21){
		if(*aceTotal > 0){
			*(total) -= 10;
			*aceTotal=*aceTotal-1;
			printf("\nThere was an Ace, so the actual value is: %d", *total);
		}
		else{
			return -1;
		}
	}
	return 1;
}
int checkComp(int *total, int *aceTotal){
	if(*total > 21){
		if(*aceTotal > 0){
			*(total) -= 10;
			*aceTotal=*aceTotal-1;
		}
		else{
			return -1;
		}
	}
	else if(*total >= 17){
		return 0;
	}
	return 1;
}
void playGame(){
	cardDeck = fopen("/dev/cards", "r");
	int stateOfGame = 1;
	int compCurrCard, currCard;
	char input[10];
	int playerHandTotal = 0;
	int playerAceTotal = 0;
	int compHandTotal = 0;
	int compAceTotal = 0;
	compHandTotal = cardDraw(&compAceTotal);
	compCurrCard = cardDraw(&compAceTotal);
	playerHandTotal = cardDraw(&playerAceTotal);
	while(stateOfGame == 1){
		printf("The dealer:\n? + %d\n\n", compCurrCard);
		currCard = cardDraw(&playerAceTotal);
		if(currCard == 11){
			currCard=changeAce(&playerHandTotal, &playerAceTotal);
		}
		checkTotal(&playerHandTotal, &playerAceTotal);
		printf("You:\n%d + %d = %d ", playerHandTotal, currCard, playerHandTotal+currCard, playerAceTotal);
		playerHandTotal+=currCard;
		stateOfGame = checkTotal(&playerHandTotal, &playerAceTotal);
		if(stateOfGame == -1){
			printf("BUSTED!");
		}
		printf("\n\n");
		if(stateOfGame == 1){
			printf("Do you want to \"hit\" or \"stand\"?");
			scanf("%s", input);
			if(strcmp(input, "hit"))
				stateOfGame = 0;
		}

	}

	if(stateOfGame == -1){
		printf("You busted, dealer wins\n");
	}

	else{
		printf("The dealer:\n%d + %d = %d\n\n",compHandTotal, compCurrCard, compHandTotal+compCurrCard);
		compHandTotal += compCurrCard;
		do{
			compCurrCard = cardDraw(&compAceTotal);
			if(compCurrCard == 11){
				compCurrCard=changeAce(&compHandTotal, &compAceTotal);
			}
			printf("The dealer:\n%d + %d = %d\n\n",compHandTotal, compCurrCard, compHandTotal+compCurrCard);
			compHandTotal += compCurrCard;
			stateOfGame = checkComp(&compHandTotal, &compAceTotal);
		}
		while(stateOfGame == 1);
		if(stateOfGame == 0){
			(playerHandTotal > compHandTotal)? printf("You won, the dealer drew %d and you drew %d", compHandTotal, playerHandTotal):printf("You Lost, the dealer drew %d and you drew %d", compHandTotal, playerHandTotal);
		}
		else{

		checkTotal(&playerHandTotal, &playerAceTotal);
			printf("Dealer Busted, you win!");
		}
	}
}
int main(){
	playGame();
	return 0;
}



