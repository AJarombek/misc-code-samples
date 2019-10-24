"""
Interview practice problem: https://leetcode.com/problems/remove-outermost-parentheses/
Description:
    A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses
    strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid
    parentheses strings.  A valid parentheses string S is primitive if it is nonempty, and there does not exist a way
    to split it into S = A+B, with A and B nonempty valid parentheses strings.  Return S after removing the outermost
    parentheses of every primitive string in the primitive decomposition of S.

Author: Andrew Jarombek
Date: 10/23/2019
"""


def remove_outer_parentheses(s):
    """
    Solve the outer parenthesis problem recursively.  The time complexity is O(n), since each index in the original
    string is observed.  The space complexity is O(m), where m is the number of recursive calls made.
    :param s: The original parenthesis string.
    :return: The parenthesis string without outer parenthesis.
    """
    if s is None or len(s) == 0:
        return ''

    left = 0
    right = 0
    for c in s:
        if c == '(':
            left += 1
        if c == ')':
            right += 1

        if left == right:
            break

    return s[1:right + left - 1] + remove_outer_parentheses(s[right + left:])


if __name__ == '__main__':
    result1 = remove_outer_parentheses('(()())(())')
    assert result1 == '()()()'

    result2 = remove_outer_parentheses('(()())(())(()(()))')
    assert result2 == '()()()()(())'

    result3 = remove_outer_parentheses('()()')
    assert result3 == ''
