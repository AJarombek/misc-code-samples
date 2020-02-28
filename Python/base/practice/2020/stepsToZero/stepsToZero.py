"""
Practice problem: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
Result: [SUCCESS - 0:01]
Description:
    Given a non-negative integer num, return the number of steps to reduce it to zero. If the current number is even,
    you have to divide it by 2, otherwise, you have to subtract 1 from it.

Author: Andrew Jarombek
Date: 2/28/2020
"""


def number_of_steps(num: int) -> int:
    count = 0
    while num > 0:
        count += 1
        if num % 2 == 0:
            num /= 2
        else:
            num -= 1
    return count
