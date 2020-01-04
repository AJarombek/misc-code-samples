"""
Class representing a node in an n-ary tree.
Author: Andrew Jarombek
Date: 12/3/2019
"""


class TreeNode:
    def __init__(self, val: int, children: list) -> None:
        """
        Construct a node in a n-ary search tree.
        :param val: Integer value that this node holds.
        :param children: A list of children nodes for this node.
        """
        self.val = val
        self.children = children
