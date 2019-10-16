"""
Interview practice problem.
Description:
    Given the root node of a search tree, return the sum of values of all nodes with value between L and R (inclusive).

Author: Andrew Jarombek
Date: 10/16/2019
"""


class TreeNode:
    def __init__(self, val: int, left, right):
        """
        Construct a node in an un-ordered search tree.
        :param val: Integer value that this node holds.
        :param left: The child node on the left side of this node.
        :param right: The child node on the right side of this node.
        """
        self.val = val
        self.left = left
        self.right = right


def range_sum_search_tree(root: TreeNode, l: int, r: int) -> int:
    """
    Solve the search tree sum problem.  The solution is recursive in nature and views every node in the tree,
    taking O(n) time.  Since its recursive (and not tail-recursive), it takes up O(n) space on the memory stack.
    :param root: The node in a search tree to operate on.
    :param l: The lower bound of the range that the node's value must be between to count towards the solution.
    :param r: The upper bound of the range that the node's value must be between to count towards the solution.
    :return: Sum of the search tree's values within the desired range.
    """
    total = 0
    if root.val >= l and root.val <= r:
        total = root.val

    if root.left is not None:
        total = total + range_sum_search_tree(root.left, l, r)
    if root.right is not None:
        total = total + range_sum_search_tree(root.right, l, r)

    return total


if __name__ == '__main__':
    # Binary Search Tree with the following elements: [10,5,15,3,7,null,18]
    node1 = TreeNode(18, None, None)
    node2 = TreeNode(0, None, None)
    node3 = TreeNode(7, None, None)
    node4 = TreeNode(3, None, None)

    node5 = TreeNode(5, node1, node2)
    node6 = TreeNode(15, node3, node4)

    rootNode = TreeNode(10, node5, node6)

    result = range_sum_search_tree(rootNode, 7, 15)
    assert result == 32
