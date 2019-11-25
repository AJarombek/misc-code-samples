"""
Interview practice problem: https://leetcode.com/problems/peak-index-in-a-mountain-array/
Result: [SUCCESS - 0:10]
Description:
    Let's call an array A a mountain if the following properties hold:

    A.length >= 3
    There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
    Given an array that is definitely a mountain, return any i such that
    A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].

Author: Andrew Jarombek
Date: 11/22/2019
"""


def peak_index_in_mountain_array(mountain: list) -> int:
    """
    Solve the problem using a for loop and variables to track the peak value.  The time complexity is O(n) where n
    is the list of the mountain array.  The space complexity is O(1).
    :param mountain: An array to find the peak value in.
    :return: The index of the peak value.
    """
    peak_index = -1
    peak = -1
    for i in range(len(mountain)):
        if mountain[i] < peak:
            break
        if mountain[i] > peak:
            peak = mountain[i]
            peak_index = i

    return peak_index


if __name__ == '__main__':
    result = peak_index_in_mountain_array([0, 1, 0])
    assert result == 1

    result2 = peak_index_in_mountain_array([0, 2, 1, 0])
    assert result2 == 1
