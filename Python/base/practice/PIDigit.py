#!/usr/bin/env python
import math
import sys

# Author: Andrew Jarombek
# Date: 7/15/2016
# Find PI up to the Nth specified digit
    
class FindPI:
    PI = math.pi
    
    def findPI(self, digit=2):
        # Format PI as a string with a specified number of characters
        return '{:.{}}'.format(self.PI, digit+1)
    
if __name__ == "__main__":
    # Convert the system argument to an integer with pythons int() method
    roundTo = int(sys.argv[1])
    fp = FindPI()
    print("PI rounded to %d decimal places is %s\n" % (roundTo, fp.findPI(roundTo)))
    print("PI rounded to 2 decimal places is %s\n" % fp.findPI())