"""
Unit Test the Practice problem: https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
Author: Andrew Jarombek
Date: 1/20/2020
"""

import unittest

from .naryTreeMaxDepth import max_depth
from datastructures.NaryTreeNode import TreeNode


class TestUnivalTree(unittest.TestCase):

    def test(self):
        tree = TreeNode(1, [TreeNode(3, [TreeNode(5), TreeNode(6)]), TreeNode(2), TreeNode(4)])
        result = max_depth(tree)
        self.assertEqual(3, result)
