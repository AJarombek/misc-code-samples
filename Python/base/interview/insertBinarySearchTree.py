"""
Interview practice problem: https://leetcode.com/problems/insert-into-a-binary-search-tree/
Description:
    Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into
    the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist
    in the original BST.

Author: Andrew Jarombek
Date: 10/20/2019
"""


class TreeNode:
    def __init__(self, val: int, left=None, right=None):
        """
        Construct a node in a binary search tree.
        :param val: Integer value that this node holds.
        :param left: The child node on the left side of this node.
        :param right: The child node on the right side of this node.
        """
        self.val = val
        self.left = left
        self.right = right


def insert_into_bst(root, val):
    """
    Implementation of an insertion into a binary search tree.  The tree is not re-balanced upon insertion.  This
    function takes O(log n) time to complete, since the tree is halved upon every iteration.  However, if the tree is
    unbalanced, this operation an take up to O(n) time.  The space complexity is also O(log n), because it's a recursive
    call that fills up the stack.  In an unbalanced tree, this can take up O(n) space.
    :param root: The root node in the tree.
    :param val: The value to be inserted as a new node in the tree.
    :return: The root node of the tree after the new value is inserted.
    """
    if val > root.val:
        if root.right is None:
            root.right = TreeNode(val)
        else:
            insert_into_bst(root.right, val)
    else:
        if root.left is None:
            root.left = TreeNode(val)
        else:
            insert_into_bst(root.left, val)

    return root


if __name__ == '__main__':
    """
        Insert '5' into the following graph:
              [4]
             /   \
          [2]     [7]
         /   \
        [1] [3]

    """
    node3 = TreeNode(3, None, None)
    node1 = TreeNode(1, None, None)

    node7 = TreeNode(7, None, None)
    node2 = TreeNode(2, node1, node3)

    node4 = TreeNode(4, node2, node7)

    insert_into_bst(node4, 5)

    assert node7.left.val == 5
