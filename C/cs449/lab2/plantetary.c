#include<stdio.h>
#include<string.h>

char line[100];
float weight;

int main(){
printf("Please insert a weight and we will tell you your weight on other planets:");
fgets(line, sizeof(line), stdin);
sscanf(line, "%f", &weight);

printf("Your weight on other planets:\n");
printf("Mercury: %.2f lbs\n", weight*.38);
printf("Mars: %.2f lbs\n", weight*.38);
printf("Venus: %.2f lbs\n", weight*.91);
printf("Jupiter: %.2f lbs\n", weight*2.54);
printf("Saturn: %.2f lbs\n", weight*1.08);
printf("Uranus: %.2f lbs\n", weight*.91);
printf("Neptune: %.2f lbs\n", weight*1.19);


return 0;
}
