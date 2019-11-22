"""
Interview practice problem: https://leetcode.com/problems/path-in-zigzag-labelled-binary-tree
Result: [SUCCESS - 0:45]
Description:
    In an infinite binary tree where every node has two children, the nodes are labelled in row order.  In the odd
    numbered rows (ie., the first, third, fifth,...), the labelling is left to right, while in the even numbered rows
    (second, fourth, sixth,...), the labelling is right to left.

    Given the label of a node in this tree, return the labels in the path from the root of the tree to the node
    with that label.

Author: Andrew Jarombek
Date: 11/22/2019
"""

import math


def path_in_zig_zag_tree(label: int) -> list:
    """
    Solve the problem using arithmetic and a while loop.  The time complexity and space complexity are O(log n),
    where n is the height of the binary tree.
    :param label: The value of a node in the tree to find the path to.
    :return: A list containing the values along a path from the root to a node in the tree.
    """
    result = []
    row = int(math.ceil(math.log(label + 1, 2)))

    while row >= 1:
        result.append(label)
        prev_first = 2 ** (row - 2)
        first = 2 ** (row - 1)

        offset = (first - prev_first - 1) - ((label - first) // 2)

        label = prev_first + offset
        row -= 1

    result.reverse()
    return result


if __name__ == '__main__':
    result1 = path_in_zig_zag_tree(14)
    assert result1 == [1, 3, 4, 14]

    result2 = path_in_zig_zag_tree(16)
    assert result2 == [1, 3, 4, 15, 16]
