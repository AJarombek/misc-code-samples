"""
Interview practice problem: https://leetcode.com/problems/array-partition-i/
Result: [SUCCESS - 0:22]
Description:
    Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2),
    ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Author: Andrew Jarombek
Date: 11/23/2019
"""


def array_pair_sum(nums: list) -> int:
    """
    Solve the problem by sorting the list and then getting the sum of every even-indexed integer.  The time complexity
    is O(n log n), which is due to the sorting algorithm.  The space complexity is O(n).
    :param nums: A list of integers.
    :return: The sum of the minimum values in two-value tuples created from the nums list.
    """
    nums.sort()
    return sum(n for i, n in enumerate(nums) if i % 2 == 0)


if __name__ == '__main__':
    result1 = array_pair_sum([1, 1])
    assert result1 == 1

    result1 = array_pair_sum([1, 4, 3, 2])
    assert result1 == 4
