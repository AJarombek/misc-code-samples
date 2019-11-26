"""
Interview practice problem: https://leetcode.com/problems/search-in-a-binary-search-tree/
Result: [SUCCESS - 0:04]
Description:
    Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the node's
    value equals the given value. Return the subtree rooted with that node. If such node doesn't exist,
    you should return NULL.

Author: Andrew Jarombek
Date: 11/26/2019
"""

from TreeNode import TreeNode


def search_bst(root: TreeNode, val: int) -> TreeNode:
    """
    Solve the problem using recursion.  The time complexity and space complexity are O(log n).
    :param root: The root node of the tree or sub-tree.
    :param val: The value to search for in the tree.
    :return: A subtree if the root nodes value is equal to val, None if the root node is None,
    or a recursive result otherwise.
    """
    if root is None:
        return None
    if root.val == val:
        return root

    if val > root.val:
        return search_bst(root.right, val)
    else:
        return search_bst(root.left, val)


if __name__ == '__main__':
    """
    Searching the following graph:

         [4]
        /   \
      [2]   [7]
      / \
    [1] [3]    

    Should result in this graph:

       [2]
      /   \
    [1]   [3]
    """
    tree = TreeNode(4, TreeNode(2, TreeNode(1, None, None), TreeNode(3, None, None)), TreeNode(7, None, None))
    result_tree = search_bst(tree, 2)

    assert all([
        result_tree.val == 2,
        result_tree.left.val == 1,
        result_tree.right.val == 3,
        result_tree.left.right is None,
        result_tree.left.left is None,
        result_tree.right.right is None,
        result_tree.right.left is None
    ])
