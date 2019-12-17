"""
String ZigZag Problem.
Author: Andrew Jarombek
Date: 12/16/2019
"""


def convert(s: str, num_rows: int):
    down = True
    prior_index = -1
    last_index = num_rows - 1

    str_list: dict = {}
    for char in s:
        if down:
            prior_index += 1
            str_list[prior_index] += char
            if prior_index == last_index:
                down = False
        else:
            prior_index -= 1
            str_list[prior_index] += char
            if prior_index == 0:
                down = True

    result = ''
    for i in range(num_rows):
        result += str_list[i]

    return result