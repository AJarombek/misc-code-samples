"""
Class representing a node in a binary tree.
Author: Andrew Jarombek
Date: 10/1/2019
"""


class TreeNode:
    def __init__(self, val: int, left, right):
        """
        Construct a node in a binary search tree.
        :param val: Integer value that this node holds.
        :param left: The child node on the left side of this node.
        :param right: The child node on the right side of this node.
        """
        self.val = val
        self.left = left
        self.right = right
