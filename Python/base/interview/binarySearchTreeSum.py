"""
Interview practice problem: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
Description:
    Given the root of a binary search tree with distinct values, modify it so that every node has a new value equal to \
    the sum of the values of the original tree that are greater than or equal to node.val.

Author: Andrew Jarombek
Date: 10/17/2019
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


def bst_to_gst(root: TreeNode) -> TreeNode:
    """
    Solve the binary search tree sum problem with recursion.  Most of the logic occurs in the helper function.  This
    implementation takes O(V+E) time (vertexes + edges), which is the standard time complexity of DFS.  The algorithm
    takes up O(V+E) space, since the call stack is populated with the recursive calls.
    :param root: The root node of the binary search tree.
    :return: The root (which holds links to the rest of the tree) with updated values.
    """
    modified_dfs(root, 0)
    return root


def modified_dfs(node: TreeNode, total: int):
    """
    Helper function which performs a modified version of DFS (Depth-First Search) on the graph.  Instead of marking
    vertexes as visited, the values in the vertexes are changed from right to left (largest values to smallest values).
    :param node: The current node to view and set the value of.
    :param total: The sum of all the vertexes visited so far.
    :return: The sum of the vertex values.
    """
    if node.right is None:
        total = total + node.val
    else:
        total = node.val + modified_dfs(node.right, total)

    node.val = total

    if node.left is None:
        return total
    else:
        return modified_dfs(node.left, total)


if __name__ == '__main__':
    """
    Test the following graph:
          [4]
        /     \
     [1]       [6]
     /   \     /   \
    [0] [2]   [5] [7]
           \         \
           [3]       [8]
     
    """
    node3 = TreeNode(3, None, None)
    node8 = TreeNode(8, None, None)

    node0 = TreeNode(0, None, None)
    node2 = TreeNode(2, None, node3)
    node5 = TreeNode(5, None, None)
    node7 = TreeNode(7, None, node8)

    node6 = TreeNode(6, node5, node7)
    node1 = TreeNode(1, node0, node2)

    node4 = TreeNode(4, node1, node6)

    bst_to_gst(node4)

    assert node4.val == 30

    assert node6.val == 21
    assert node1.val == 36

    assert node0.val == 36
    assert node2.val == 35
    assert node5.val == 26
    assert node7.val == 15

    assert node3.val == 33
    assert node8.val == 8
