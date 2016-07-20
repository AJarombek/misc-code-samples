# Author: Andrew Jarombek
# Date: 7/19/2016
# Enter a number and have the program generate the Fibonacci sequence to that number or to the Nth number.

class Fibonacci
	# Instance variable for the fibonacci sequence array
	attr_accessor :sequence

	def gen_fibonacci(n)
		if n <= 0
			puts "The ##{n} fibonacci number is #{sequence[0]}"
			return sequence[0]
		elsif n == 1
			puts "The ##{n} fibonacci number is #{sequence[1]}"
			return sequence[1]
		else
			# Similar to a for loop in other languages
			(n-1).times do |i|
				# adds the value of the expression to the end of the array 
				sequence << (sequence[i+1] + sequence[i])
			end
		end
		puts "The ##{n} fibonacci number is #{sequence[n]}"
		return sequence[n]
	end
end

fibnumber = Integer(ARGV[0])
f = Fibonacci.new
# Initialize the instance variables
f.sequence = [0, 1]
f.gen_fibonacci(fibnumber)
