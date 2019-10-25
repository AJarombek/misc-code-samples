"""
Interview practice problem: https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
Description:
    Return the root node of a binary search tree that matches the given preorder traversal.

    (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a
    value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal
    displays the value of the node first, then traverses node.left, then traverses node.right.)

Author: Andrew Jarombek
Date: 10/25/2019
"""

from TreeNode import TreeNode


def bst_from_preorder(preorder):
    """
    Solve the problem with a helper function which runs recursively.  The runtime is O(n log n), because each item in
    the list is processed [O(n)] and then added into the binary search tree [O(log n)].
    :param preorder: The list to convert into a binary search tree.
    :return: The root node of the new binary search tree.
    """
    root = TreeNode(preorder[0], None, None)

    for item in preorder[1:]:
        add_to_tree(root, item)

    return root


def add_to_tree(root, item):
    """
    Add a node into a binary search tree using recursion.
    :param root: The root node of the tree or subtree.
    :param item: The value of a new node to add to the tree.
    :return: void.
    """
    if item < root.val:
        if root.left is None:
            root.left = TreeNode(item, None, None)
            return
        else:
            return add_to_tree(root.left, item)
    else:
        if root.right is None:
            root.right = TreeNode(item, None, None)
            return
        else:
            return add_to_tree(root.right, item)


if __name__ == '__main__':
    """
    The following list:
        [8, 5, 1, 7, 10, 12]
    Should result in the following graph:
          [8]
         /   \
      [5]     [10]
     /   \        \
    [1] [7]      [12]
     
    """
    result = bst_from_preorder([8, 5, 1, 7, 10, 12])

    assert result.val == 8

    assert result.left.val == 5
    assert result.right.val == 10

    assert result.left.left.val == 1
    assert result.left.right.val == 7

    assert result.right.right.val == 12
