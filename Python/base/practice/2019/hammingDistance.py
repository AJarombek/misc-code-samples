"""
Python solution to the hamming distance problem which I also answered in Java.
Author: Andrew Jarombek
Date: 11/16/2019
"""


def hamming_distance(x: int, y: int) -> int:
    """
    This solution uses a bitwise XOR to find the non-shared bits and then shifts through the bits to count the
    total non-shared bits.
    :param x: The first integer.
    :param y: The second integer.
    :return: The hamming distance between the two integers.
    """
    distance = 0
    xor = x ^ y

    while xor > 0:
        if xor % 2 != 0:
            distance += 1
        xor = xor >> 1

    return distance


if __name__ == '__main__':
    result = hamming_distance(4, 1)
    assert result == 2
