"""
Practice problem: https://leetcode.com/problems/univalued-binary-tree/
Description:
    A binary tree is univalued if every node in the tree has the same value.  Return true if and only if the given tree
    is univalued.

Author: Andrew Jarombek
Date: 1/19/2020
"""

from datastructures.TreeNode import TreeNode


def is_unival_tree(root: TreeNode) -> bool:
    """
    Solve the problem with a helper function that recursively searches through the tree for values different than the
    root tree node value.
    :param root: The root node of the tree.
    :return: True if every node in the tree has the same value, False otherwise.
    """
    return is_unival_tree_search(root, root.val)


def is_unival_tree_search(root: TreeNode, val: int) -> bool:
    """
    Recursive function which determines if all nodes have the same value.
    :param root: The root node of the tree (or subtree).
    :param val: The value of the root tree node.
    :return: True if every node in the tree or subtree has the same value, False otherwise.
    """
    if root is None:
        return True
    else:
        return root.val == val and is_unival_tree_search(root.left, val) and is_unival_tree_search(root.right, val)