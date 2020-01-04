"""
Interview practice problem: https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
Result: [SUCCESS - 0:20]
Description:
    Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
    Return the smallest level X such that the sum of all the values of nodes at level X is maximal.

Author: Andrew Jarombek
Date: 11/12/2019
"""

import collections
from TreeNode import TreeNode


def max_level_sum(root: TreeNode) -> int:
    """
    Solve the problem using a dictionary and recursion.  The time complexity is O(n log n), since each item in the
    binary tree is observed and then each dictionary element is viewed.  The length of this dictionary is the
    height of the tree (log n where n is the # of elements).  The space complexity is O(n), since the call stack
    is populated for each item in the tree.
    :param root: The root node of the binary tree.
    :return: The level of the binary tree with the maximum sum of values.
    """
    level_sum_dict = level_sum(root, 1, collections.defaultdict(int))

    result_level = None
    max_sum = None
    for level in level_sum_dict:
        if result_level is None or level_sum_dict[level] > max_sum:
            result_level = level
            max_sum = level_sum_dict[level]
    return result_level


def level_sum(root, level, level_sum_dict):
    """
    Recursive helper function which creates a dictionary with key->value pairs of level->sum.
    :param root: Root node of the binary tree.
    :param level: Level of the binary tree currently being viewed.  The first level is 1.
    :param level_sum_dict: The work in progress dictionary of level->sum pairs.
    :return: The final dictionary of level->sum pairs.
    """
    level_sum_dict[level] += root.val
    if root.left is not None:
        level_sum(root.left, level + 1, level_sum_dict)
    if root.right is not None:
        level_sum(root.right, level + 1, level_sum_dict)
    return level_sum_dict


if __name__ == '__main__':
    result = max_level_sum(
        TreeNode(1,
                 TreeNode(7,
                          TreeNode(7, None, None),
                          TreeNode(-8, None, None)
                          ),
                 TreeNode(0, None, None)
                 )
    )

    assert result == 2
