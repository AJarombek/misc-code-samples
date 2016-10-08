#include <stdio.h>
#include <string.h>

// Author: Andrew Jarombek
// Date: 9/15/2016 - 10/8/2016
// Have the program find prime numbers until the user chooses to stop asking for the next one.

#define MAXINPUT 10

int checkInput(char input[], int *prime);
int nextPrime(int *prev);

/* Main method for user input */
int main() {

	int done, prime;

	done = 1;
	prime = 1;

	// While the user hasn't typed in 'n', continue searching for primes
	while (done) {
		char input[MAXINPUT];
		printf("Find the next prime number (Y/n): \n");
		scanf("%s", &input);
		done = checkInput(input, &prime);
	}
}

/* Analyze the user input and choose which action to take */
int checkInput(char input[], int *prime) {
	if (strlen(input) == 1) {
		// If input is 'Y', search for the next prime
		if (input[0] == 'Y') {
			nextPrime(prime);
			return 1;
		// If input is 'N', end search
		} else if (input[0] == 'n') {
			printf("Goodbye.\n");
			return 0;
		// Otherwise give an error message
		} else {
			printf("ERROR: Invalid Command.\n");
			return 1;
		}
	} else {
		printf("ERROR: Invalid Command.\n");
		return 1;
	}
}

/* Find the next prime number */
int nextPrime(int *prev) {
	int found = 0;
	*prev += 1;

	// Base case, 2 is prime
	if (*prev == 2) {
		printf("The next prime is: %d\n", *prev);
	} else {
		// check to see if it is divisible my any numbers besides itself & 1
		for (int i = 2; i <= *prev/2; i++) {
			if (*prev % i == 0) {
				found = nextPrime(prev);
			}
		}

		// This makes sure the prime number is not printed more than once
		// Since there can be many recursive calls occuring 
		if (!found) {
			printf("The next prime is: %d\n", *prev);
			return 1;
		}
	}
}