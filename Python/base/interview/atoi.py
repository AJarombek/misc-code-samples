"""
Interview practice problem.
Description:
    Implement atoi which converts a string to an integer.

    The function first discards as many whitespace characters as necessary until the first non-whitespace character is
    found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many
    numerical digits as possible, and interprets them as a numerical value.

Author: Andrew Jarombek
Date: 10/12/2019
"""

import re


def atoi(string: str) -> int:
    """
    Parse an integer from a string.
    :param string: The string to convert to an integer.
    :return: An integer representation of the string.
    """
    if string is None or len(string) == 0:
        return 0

    min_val = -2 ** 31
    max_val = 2 ** 31 - 1

    number_pattern = re.compile('[0-9\-+]')

    prev_char_sign = False
    number_str = ''

    for c in string:
        if number_pattern.match(c):
            if c == '-':
                if prev_char_sign or len(number_str) > 0:
                    break

                prev_char_sign = True
                number_str = number_str + c
            elif c == '+':
                if prev_char_sign or len(number_str) > 0:
                    break

                prev_char_sign = True
                number_str = number_str + c
            else:
                number_str = number_str + c
        elif c == ' ' and len(number_str) > 0:
            break
        elif not c == ' ':
            break

    if number_str != '' and number_str != '-' and number_str != '+':
        result = int(number_str)
    else:
        return 0

    if result < min_val:
        return min_val
    elif result > max_val:
        return max_val
    else:
        return result


if __name__ == '__main__':
    result = atoi('   -50')
    print(result)
