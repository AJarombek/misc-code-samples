"""
Interview practice problem.
Description & Example: https://leetcode.com/problems/zigzag-conversion/

Author: Andrew Jarombek
Date: 10/11/2019
"""


def convert(s: str, num_rows: int) -> str:
    """
    Convert a string into a zig-zagging string.
    :param s: The initial string.
    :param num_rows: The number of rows over which to zig-zag.
    :return: The string that results from zig-zagging.
    """

    # Base case when the input string doesn't exist or is empty.
    if s is None or len(s) == 0:
        return ""

    # Base cases where the string doesn't zigzag.
    if num_rows <= 1 or len(s) <= num_rows:
        return s

    zig_zag_dict = {}

    next_index = 0
    down = True

    for c in s:
        zig_zag_dict.setdefault(next_index, "")
        zig_zag_dict[next_index] = zig_zag_dict[next_index] + c

        if down:
            if next_index == num_rows - 1:
                down = False
                next_index = next_index - 1
            else:
                next_index = next_index + 1
        elif not down:
            if next_index == 0:
                down = True
                next_index = next_index + 1
            else:
                next_index = next_index - 1

    result = ""
    for i in range(num_rows):
        result = result + zig_zag_dict[i]

    return result


if __name__ == '__main__':
    zig_zag_result = convert('HIMYNAMEISANDY', 3)
    print(zig_zag_result)
