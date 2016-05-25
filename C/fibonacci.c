/*
 * Author: Andrew Jarombek
 * Date: 5/24/2016
 * Fibonacci Sequence - Enter a number and have the program generate the Fibonacci sequence
 * to that number or to the Nth number.
*/

#include <stdio.h>
#include <stdlib.h>

void fibonacci(int num);

int main() {
    
    int n;
    printf("Enter the Length of the Fibonacci Sequence: ");
    scanf("%d", &n);
    
    fibonacci(n);
}

void fibonacci(int num) {
    
    unsigned long long *seq = malloc((num+1) * sizeof *seq);
    
    if (num < 0) {
        printf("ERROR: Invalid Fibonacci Sequence Length.");
    }
    
    if (num >= 0) {
        seq[0] = 0;
    }
    if (num >= 1) {
        seq[1] = 1;
    }
    
    // Put all values into seq array until it is full
    int count = 2;
    while (count <= num) {
        seq[count] = seq[count-1] + seq[count-2];
        count++;
    }
    
    // Print out all of the seq array elements
    for (int i=0; i<=num; i++) {
        printf("%llu\n", seq[i]);
    }
    
    free(seq);
}