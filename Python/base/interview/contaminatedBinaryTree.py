"""
Interview practice problem: https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/
Result: [SUCCESS (WITH HINT) - 0:40]
Description:
    Given a binary tree with the following rules:

    root.val == 0
    If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
    If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
    Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.

    You need to first recover the binary tree and then implement the FindElements class:

    FindElements(TreeNode* root) Initializes the object with a contaminated binary tree, you need to recover it first.
    bool find(int target) Return if the target value exists in the recovered binary tree.

Author: Andrew Jarombek
Date: 11/18/2019
"""

from TreeNode import TreeNode


class FindElements:

    def __init__(self, root: TreeNode):
        """
        Decontaminate a binary tree.
        :param root: The root node of a binary tree.
        """
        self.tree = root

        # Use a set as a cache for the contents of the binary tree.
        self.memo = set()
        self.decontaminate(self.tree, 0)

    def decontaminate(self, node: TreeNode, val: int) -> None:
        """
        Decontaminate the binary tree using recursion.
        :param node: A node in a binary tree.
        :param val: The value to assign to this node.
        """
        node.val = val

        # After my first solution was too slow, I added a cache of all the items in the tree.
        self.memo.add(val)

        if node.left is not None:
            self.decontaminate(node.left, 2 * val + 1)
        if node.right is not None:
            self.decontaminate(node.right, 2 * val + 2)

    def find(self, target: int) -> bool:
        """
        Find a value in the decontaminated binary tree using the set cache.  This is an O(1) operation.
        :param target: The integer value to find in the tree.
        :return: True if the value is in the tree, False otherwise.
        """
        return target in self.memo

    def find_v1(self, target: int) -> bool:
        """
        Find a value in the decontaminated binary tree using recursion.  This is an O(n) operation.
        :param target: The integer value to find in the tree.
        :return: True if the value is in the tree, False otherwise.
        """
        return self.find_recursive(self.tree, target)

    def find_recursive(self, node: TreeNode, target: int) -> bool:
        """
        Recursive helper function for my initial solution.
        :param node: A node in the tree to search for the value on.
        :param target: The integer value to find in the tree.
        :return: True if the value is in the tree, False otherwise.
        """
        if node is None or node.val > target:
            return False
        if node.val == target:
            return True

        if node.left is not None:
            in_left = self.find_recursive(node.left, target)
        else:
            in_left = False

        if node.right is not None:
            in_right = self.find_recursive(node.right, target)
        else:
            in_right = False

        return in_left or in_right


if __name__ == '__main__':
    find_elements = FindElements(TreeNode(-1, None, TreeNode(-1, None, None)))

    assert not find_elements.find_v1(1)
    assert find_elements.find_v1(2)

    assert not find_elements.find(1)
    assert find_elements.find(2)
