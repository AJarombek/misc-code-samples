"""
Interview practice problem: https://leetcode.com/problems/self-dividing-numbers/.
Result: [SUCCESS - 0:12]
Description:
    A self-dividing number is a number that is divisible by every digit it contains.  For example, 128 is a
    self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.  Also, a self-dividing number is not
    allowed to contain the digit zero.  Given a lower and upper number bound, output a list of every possible self
    dividing number, including the bounds if possible.

Author: Andrew Jarombek
Date: 11/5/2019
"""


def self_dividing_numbers(left: int, right: int) -> list:
    """
    Solution for the self dividing numbers problem.  The time complexity is O(nm) where n is (right - left) and m is the
    number of digits in 'right'.  If right is < 10, the time complexity is O(n).  The space complexity is O(n) since a
    result list is created and populated.
    :param left: The smallest number to check for self-dividing.
    :param right: The largest number to check for self-dividing.
    :return: A list of self dividing numbers between 'left' and 'right'.
    """
    result = []
    for num in range(left, right + 1):
        original_num = num
        while num > 0:
            digit = num % 10

            if digit == 0 or original_num % digit != 0:
                break

            num = num // 10

        if num == 0:
            result.append(original_num)

    return result


if __name__ == '__main__':
    result_list = self_dividing_numbers(1, 22)
    assert result_list == [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
