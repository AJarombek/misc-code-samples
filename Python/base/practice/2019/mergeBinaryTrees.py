"""
Interview practice problem: https://leetcode.com/problems/merge-two-binary-trees/
Result: [SUCCESS - 0:25]
Description:
    Given two binary trees, imagine that when you put one of them to cover the other, some nodes of the two trees are
    overlapped while the others are not.  You need to merge them into a new binary tree. The merge rule is that if two
    nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be
    used as the node of new tree.

Author: Andrew Jarombek
Date: 11/9/2019
"""

from TreeNode import TreeNode


def merge_trees(t1: TreeNode, t2: TreeNode) -> TreeNode:
    """
    Solve the problem using recursion.  The time complexity is O(n), where n is the size of the largest original array.
    The space complexity is also O(n), since a new binary tree is created (and recursive calls are placed on the stack).
    :param t1: The first binary tree to be merged.
    :param t2: The second binary tree to be merged.
    :return: The root node of the new binary tree.
    """
    if t1 is None and t2 is None:
        return None
    else:
        return merge(t1, t2, TreeNode(0, None, None))


def merge(t1: TreeNode, t2: TreeNode, result: TreeNode) -> TreeNode:
    """
    Helper function used to solve the problem.
    :param t1: The first binary tree to be merged.
    :param t2: The second binary tree to be merged.
    :param result: The new binary tree.
    :return: The root node of the new binary tree.
    """
    if t1 is None:
        t1 = TreeNode(0, None, None)
    if t2 is None:
        t2 = TreeNode(0, None, None)

    result.val = t1.val + t2.val

    if t1.left is not None or t2.left is not None:
        result.left = TreeNode(0, None, None)
        merge(t1.left, t2.left, result.left)

    if t1.right is not None or t2.right is not None:
        result.right = TreeNode(0, None, None)
        merge(t1.right, t2.right, result.right)

    return result


if __name__ == '__main__':
    """
    Merging the following two graphs:
    
         [1]
        /   \
      [3]   [2]
      /        
    [5]    
    
         [2]
        /   \
      [1]   [3]
        \      \
         [4]   [7]     
    
    Should result in this graph:
    
         [2]
        /   \
      [1]   [3]
      / \      \
    [5] [4]    [7] 
    """
    tree1 = TreeNode(1, TreeNode(3, TreeNode(5, None, None), None), TreeNode(2, None, None))
    tree2 = TreeNode(2, TreeNode(1, None, TreeNode(4, None, None)), TreeNode(3, None, TreeNode(7, None, None)))

    result = merge_trees(tree1, tree2)

    assert all([
        result.val == 3,
        result.left.val == 4,
        result.left.left.val == 5,
        result.left.right.val == 4,
        result.right.val == 5,
        result.right.right.val == 7,
    ])