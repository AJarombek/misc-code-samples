"""
Interview practice problem: https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
Description:
    In an array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.
    Return the element repeated N times.

Author: Andrew Jarombek
Date: 10/28/2019
"""


def repeated_n_times(a: list) -> int:
    """
    Solve the problem using a set and a for loop.  The time and space complexity is O(n), although it will often
    take a shorter time.
    :param a: The input array with N+1 unique elements.
    :return: The element that was repeated.
    """
    found = set()
    for item in a:
        if item in found:
            return item
        else:
            found.add(item)


if __name__ == '__main__':
    result1 = repeated_n_times([1, 2, 2, 3])
    assert result1 == 2

    result2 = repeated_n_times([3, 5, 7, 5, 5, 9, 11, 5])
    assert result2 == 5
