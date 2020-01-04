"""
Interview practice problem: https://leetcode.com/problems/squares-of-a-sorted-array/
Description:
    Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also
    in sorted non-decreasing order.

Author: Andrew Jarombek
Date: 11/8/2019
"""


def sorted_squares(nums: list) -> list:
    """
    Solve the problem using simple mapping and sorting.  The time complexity is O(n logn), since that is the time
    complexity of Quick sort.  The space complexity is O(n), since a new integer array is created.
    :param nums: Array of integers sorted in ascending order.
    :return: Sorted array of squared integers.
    """
    result = [abs(num) ** 2 for num in nums]
    result.sort()
    return result


def sorted_squares_pointers(nums: list) -> list:
    """
    Suggested answer that looks at the positive and negative values in the original array separately using two
    index pointers.  For small lists, this is actually a slower approach.  However for large lists, it is likely
    faster because the sorting algorithm is avoided, reducing the time complexity to O(n).  The reason why small lists
    are slower is due to additional operations and an initial iteration of the list to determine the initial pointer
    locations.
    :param nums: Array of integers sorted in ascending order.
    :return: Sorted array of squared integers.
    """
    result = []
    n = len(nums)

    i = -1
    while (i + 1) < n and nums[i + 1] < 0:
        i += 1

    j = i + 1

    while i >= 0 and j < n:
        if nums[i] ** 2 < nums[j] ** 2:
            result.append(nums[i] ** 2)
            i -= 1
        else:
            result.append(nums[j] ** 2)
            j += 1

    while i >= 0:
        result.append(nums[i] ** 2)
        i -= 1

    while j < n:
        result.append(nums[j] ** 2)
        j += 1

    return result


if __name__ == '__main__':
    result1 = sorted_squares([1])
    assert result1 == [1]
    assert result1 == sorted_squares_pointers([1])

    result2 = sorted_squares([0, 2])
    assert result2 == [0, 4]
    assert result2 == sorted_squares_pointers([0, 2])

    result3 = sorted_squares([-4, -1, 0, 3, 10])
    assert result3 == [0, 1, 9, 16, 100]
    assert result3 == sorted_squares_pointers([-4, -1, 0, 3, 10])
