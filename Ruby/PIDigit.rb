# Author: Andrew Jarombek
# Date: 7/19/2016
# Enter a number and have the program generate PI up to that many decimal places.
# Keep a limit to how far the program will go. (Correct answer up to 48 decimal places)
# [CMD] Usage: > ruby PIDigit.rb [decimalPlaces]

class PIDigit
    # Ruby uses snakecase for varaible/method names 
    def get_pi_digit(digits)
        # Get the PI constant from the math library
        pi = Math::PI
        # You can put variables into strings by using the #{} command
        puts "PI with #{digits} digits is %.#{digits}f" % pi
    end
end

# Get the first command line argument
dig = ARGV[0]
p = PIDigit.new
p.get_pi_digit(dig)