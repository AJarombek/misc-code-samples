#!/usr/bin/env python
import sys

# Author: Andrew Jarombek
# Date: 7/15/2016
# Have the program find prime numbers until the user chooses to stop asking for the next one.

class PrimeFactory:
    
    # Keeps track of where we currently are in our search for primes
    numberOn = 0
    
    # You can change the number to start your search at by specifying so in the constructor 
    def __init__(self, num=0):
        self.numberOn = num-1
    
    # Searches for the next prime number after the one currently found
    def nextPrime(self):
        self.numberOn += 1
        if self.isPrime(self.numberOn):
            return self.numberOn
        return self.nextPrime()
    
    # Helper function that returns true if a number is prime, and false if it isnt
    def isPrime(self, num):
        if num < 2:
            return False
        if num == 2:
            return True
        else:
            # In Python 3, Integers are divided as floating point numbers (ex. 5/2 = 2.5)
            # In order to do integer division, you must use the '//' operator
            for index in range(2, (num // 2)+1):
                if (num % index) is 0:
                    return False
            return True
    

if __name__ == '__main__':
    pf = PrimeFactory(0)
    
    searching = True;
    
    # Loop runs while the user still wants to search for primes
    while searching:
        # Put the user input into the search variable
        search = input("Search for next prime? (Y/n)")
        if search is "n":
            searching = False;
        elif search is "Y":
            print("%d\n" % pf.nextPrime())
        else:
            print("ERROR: Invalid Command")
            
    print("\nGoodbye.\n")