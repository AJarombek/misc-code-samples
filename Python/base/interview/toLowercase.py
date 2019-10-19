"""
Interview practice problem.  https://leetcode.com/problems/to-lower-case/
Description:
    Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.

Author: Andrew Jarombek
Date: 10/19/2019
"""


def to_lowercase(string):
    """
    Convert a string to lowercase.  This function takes O(n) time, since it loops through each character in a string.
    It also takes up O(n) space, since strings are immutable and a new string is created when performing the '+='
    operator.  This can be optimized with a string builder implementation (However, the Python compiler performs some
    optimizations when concatenating strings - https://stackoverflow.com/a/19926932).
    :param string:
    :return:
    """
    new_string = ''
    for char in string:
        if 65 <= ord(char) <= 90:
            new_string += chr(ord(char) + 32)
        else:
            new_string += char
    return new_string


if __name__ == '__main__':
    string1 = 'Hello'
    string1_lower = to_lowercase(string1)
    assert string1_lower == 'hello'

    string2 = 'My name is Andy'
    string2_lower = to_lowercase(string2)
    assert string2_lower == 'my name is andy'
