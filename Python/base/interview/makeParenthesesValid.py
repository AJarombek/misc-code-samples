"""
Interview practice problem: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
Result: [SUCCESS - 0:31]
Description:
    Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any
    positions ) so that the resulting parentheses string is valid.

~ after a tough day im thankful to have my little horse dotty to snuggle up with ~
Author: Andrew Jarombek
Date: 11/14/2019
"""


def min_add_to_make_valid(s: str) -> int:
    """
    Solve the problem using left and right counters and a for loop.  The runtime is O(n) because of the for loop
    and the space complexity is O(1) because only two integer variables and a single boolean variable are
    maintained.
    :param s: The initial parentheses string.
    :return: The number of parentheses that need to be added to the parentheses string to close all the open
    parentheses.
    """
    total = 0

    swap_next = False
    num_left = 0
    num_right = 0
    for c in s:
        if swap_next and c == '(':
            total += abs(num_left - num_right)
            swap_next = False
            num_left = 0
            num_right = 1
        else:
            if c == ')':
                num_left += 1
                if num_left > num_right:
                    swap_next = True
            elif c == '(':
                num_right += 1
    else:
        total += abs(num_left - num_right)

    return total


if __name__ == '__main__':
    result1 = min_add_to_make_valid('())')
    assert result1 == 1

    result2 = min_add_to_make_valid('(((')
    assert result2 == 3

    result3 = min_add_to_make_valid('()')
    assert result3 == 0

    result4 = min_add_to_make_valid('()))((')
    assert result4 == 4

    result5 = min_add_to_make_valid('(()())(')
    assert result5 == 1
