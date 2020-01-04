"""
Interview practice problem: https://leetcode.com/problems/binary-tree-pruning/
Description:
    We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
    Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

Author: Andrew Jarombek
Date: 11/3/2019
"""

from TreeNode import TreeNode


def prune_tree(root: TreeNode) -> TreeNode:
    """
    Solve the prune tree problem with recursion.  The time and space complexity are both O(n), where n is the number
    of nodes in the tree.
    :param root: Root node of the binary tree.
    :return: Root node of the binary tree after pruning nodes.
    """
    if root.left is not None:
        root.left = prune_tree(root.left)
    if root.right is not None:
        root.right = prune_tree(root.right)

    if root.val == 0 and root.left is None and root.right is None:
        return None
    else:
        return root


if __name__ == '__main__':
    """
    Test the following graph:
       [1]
      /   \
    [1]   [0]
         /   \
       [0]   [1]

    Which results in this pruned graph:
       [1]
      /   \
    [1]   [0]
             \
             [1]
    """
    right_left_node = TreeNode(0, None, None)
    right_right_node = TreeNode(1, None, None)

    right_node = TreeNode(0, right_left_node, right_right_node)

    root_node = TreeNode(1, None, right_node)

    root_result = prune_tree(root_node)

    assert root_result.val == 1

    assert root_result.left is None
    assert root_result.right.val == 0

    assert root_result.right.left is None
    assert root_result.right.right.val == 1
