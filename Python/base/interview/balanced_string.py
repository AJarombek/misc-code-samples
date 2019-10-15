"""
Interview practice problem.
Description:
    Balanced strings are those who have equal quantity of 'L' and 'R' characters.  Given a balanced string s split it
    in the maximum amount of balanced strings.  Return the maximum amount of splitted balanced strings.

Author: Andrew Jarombek
Date: 10/15/2019
"""


def balanced_string_split(s: str) -> int:
    """
    Solve the balanced string problem.  It takes O(n) time (iterating a list) and stores O(1) data
    (three integer variables).
    :param s: The balanced string to split.
    :return: The number of balanced substrings.
    """
    r = 0
    l = 0
    result = 0

    for c in s:
        if c == 'R':
            r = r + 1
        if c == 'L':
            l = l + 1
        if min(r, l) > 0 and r == l:
            result = result + 1
            r = 0
            l = 0

    return result


if __name__ == '__main__':
    result1 = balanced_string_split('RLRRLLRLRL')
    assert result1 == 4

    result2 = balanced_string_split('RLLLLRRRLR')
    assert result2 == 3

    result3 = balanced_string_split('LLLLRRRR')
    assert result3 == 1

    result4 = balanced_string_split('RRLRRLRLLLRL')
    assert result4 == 2
