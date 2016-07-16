#!/usr/bin/env python
import sys

# Author: Andrew Jarombek
# Date: 7/15/2016
# Enter a number and have the program generate the Fibonacci sequence to that
# number or to the Nth number.

class Fibonacci:
    
    fibs = [0,1]
    
    def fibSequence(self, length):
        # Make sure user is trying to find a positive fibonacci number
        if length < 0:
            sys.stderr.write("Usage: %s [fibLength] (length must be positive)\n" % sys.argv[0])
            sys.exit(1)
        
        if length is 0:
            return self.fibs[:0]
        
        for index in range(1, length):
            self.fibs.append(self.fibs[index-1] + self.fibs[index])
        return self.fibs[1:]

if __name__ == '__main__':
    
    # If no length is specified display an error message and exit
    if not sys.argv[1:]:
        sys.stderr.write("Usage: %s [fibLength]\n" % sys.argv[0])
        sys.exit(1)
    
    seqLength = int(sys.argv[1])
        
    f = Fibonacci()
    print("%s\n" % str(f.fibSequence(seqLength)))