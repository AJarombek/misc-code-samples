"""
Multiplying two numbers without multiplication operator.
Author: Andrew Jarombek
Date: 12/11/2019
"""


def mult(val1: int, val2: int):
    """
    Solve the problem by looping through one value and adding the other value to an accumulator for each loop.
    :param val1: The first integer value.
    :param val2: The second integer value.
    :return: The first integer multiplied by the second integer.
    """
    result = 0

    for i in range(abs(val2)):
        result += abs(val1)

    if (val1 >= 0 and val2 < 0) or (val1 < 0 and val2 >= 0):
        result = 0 - result

    return result


if __name__ == '__main__':
    result = mult(3, 4)
    assert result == 12

    result = mult(3, -4)
    assert result == -12

    result = mult(-3, 4)
    assert result == -12

    result = mult(-3, -4)
    assert result == 12
