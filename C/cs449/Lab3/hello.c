#include <stdio.h>

void set_zero(int a[], int n) {
	int i;

	for (i = 0; i < 9; i++) {
		a[i] = n;
	}
}

int main() {
	int array[10];
	int num;
	
	printf("Enter the value for the array: ");
	scanf("%d", &num);

	set_zero(array, num);

	return 0;
}

