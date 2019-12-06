"""
Practice problem: https://leetcode.com/problems/n-ary-tree-postorder-traversal
Description:
    Students are asked to stand in non-decreasing order of heights for an annual photo.  Return the minimum number of
    students not standing in the right positions.  (This is the number of students that must move in order for all
    students to be standing in non-decreasing order of height.)

Author: Andrew Jarombek
Date: 12/5/2019
"""


def height_checker(heights: list) -> int:
    """
    Solve the height checker problem by comparing the sorted height array with the original height array.  The
    time complexity is O(n log n) and the space complexity is O(n).
    :param heights: A list of heights of students.
    :return: The number of students that must move.
    """
    return sum(1 for x, y in zip(heights, sorted(heights)) if x != y)


if __name__ == '__main__':
    result = height_checker([1, 1, 4, 2, 1, 3])
    assert result == 3
