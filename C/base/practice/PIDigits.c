/*
 * Author: Andrew Jarombek
 * Date: 5/24/2016
 * Find PI to the Nth Digit - Enter a number and have the program generate PI up to that many decimal places.
 * Keep a limit to how far the program will go.
*/

#include <stdio.h>
#include <stdlib.h>

void findPI(int num);
const double PI = 4.0 * atan(1.0);

main() {
    
    int n;
    printf("PI Decimal Precision: ");
    scanf("%d", &n);
    
    findPI(n);
}

void findPI(int num) {
    
    // Make sure precision specified is legal
    if (num > 16 || num < 0) {
        printf("ERROR: Illegal PI Precision Value.  Precision must be from 0 to 16.\nYou Entered: %d\n", num);
        exit(EXIT_SUCCESS);
    }
    
    // C can not change floating point precision, but it can print out floating points with
    // a certain number of decimal places
    printf("PI Represented With %d Decimal Places: %.*f \n", num, num, PI); 
}