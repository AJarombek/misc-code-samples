"""
Unit Test the Practice problem: https://leetcode.com/problems/univalued-binary-tree/
Author: Andrew Jarombek
Date: 1/19/2020
"""

import unittest

from .univalTree import is_unival_tree
from datastructures.TreeNode import TreeNode


class TestUnivalTree(unittest.TestCase):

    def testTrue(self):
        tree = TreeNode(1, TreeNode(1, TreeNode(1), TreeNode(1)), TreeNode(1, None, TreeNode(1)))
        result = is_unival_tree(tree)
        self.assertTrue(result)

    def testFalse(self):
        tree = TreeNode(2, TreeNode(2, TreeNode(5), TreeNode(2)), TreeNode(2))
        result = is_unival_tree(tree)
        self.assertFalse(result)


if __name__ == '__main__':
    unittest.main()
