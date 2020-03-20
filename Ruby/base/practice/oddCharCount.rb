# Practice Problem: https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts
# Description:
#   Given an integer n, return a string with n characters such that each character in such string occurs an odd number
#   of times.  The returned string must contain only lowercase English letters. If there are multiples valid strings,
#   return any of them.
#
# @author Andrew Jarombek
# @since 3/18/2020

def assert(b)
  unless b
    raise "assert-fail"
  end
end

def generate_the_string(n)
  if n % 2 == 0
    return "a" + ("b" * (n - 1))
  else
    return "a" * n
  end
end

odd_result = generate_the_string(5)
assert(odd_result == 'aaaaa')

even_result = generate_the_string(4)
assert(even_result == 'abbb')
