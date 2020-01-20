"""
Practice problem: https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
Description:
    Given a n-ary tree, find its maximum depth.  The maximum depth is the number of nodes along the longest path from
    the root node down to the farthest leaf node.

Author: Andrew Jarombek
Date: 1/20/2020
"""

from datastructures.NaryTreeNode import TreeNode


def max_depth(root: TreeNode) -> int:
    """
    Solve the problem using a recursive helper function.  The time complexity is O(n) where n is the number
    of nodes in the tree.  The space complexity is O(n), since there are n recursive calls.
    :param root: The root node in the n-ary tree.
    :return: The height of the n-ary tree.
    """
    return max_depth_search(root, 1)


def max_depth_search(root: TreeNode, depth: int) -> int:
    """
    Helper function which recursively searches through each subtree, finding the maximum depth.
    :param root: The root node in the n-ary tree (or subtree).
    :param depth: The current depth of the recursive call within the n-ary tree.
    :return: The height of the n-ary tree (or subtree).
    """
    if root is None:
        return depth - 1
    elif root.children is None or len(root.children) == 0:
        return depth
    else:
        child_depth = depth + 1
        return max(max_depth_search(child, child_depth) for child in root.children)
