"""
Interview practice problem: https://leetcode.com/problems/maximum-binary-tree/
Description:
    Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
        The root is the maximum number in the array.
        The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
        The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
    Construct the maximum tree by the given array and output the root node of this tree.

Author: Andrew Jarombek
Date: 10/21/2019
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


def construct_maximum_binary_tree(nums):
    """
    Solve the maximum binary tree problem recursively.  This function is called more than length of 'nums' (n) times,
    however it short circuits often.  The expensive calls, which go through the for loop, execute n times.  This makes
    the time complexity of this operation O(n^2).  The space complexity is O(n), since a node in a binary tree is
    created for each item in 'nums'.
    :param nums: A list of numbers to build a maximum binary tree from.
    :return: The root node of a maximum binary tree.
    """
    if nums is None or len(nums) == 0:
        return None

    largest = None
    largest_index = None
    for index, num in enumerate(nums):
        if largest is None or num > largest:
            largest = num
            largest_index = index

    node = TreeNode(largest)
    node.left = construct_maximum_binary_tree(nums[:largest_index])
    node.right = construct_maximum_binary_tree(nums[largest_index + 1:])
    return node


if __name__ == '__main__':
    """
    From this list:
        [3,2,1,6,0,5]
    Construct the following graph:
          [6]
         /   \
      [3]     [5]
        \     /
       [2]   [0]
         \
        [1]
    """
    numbers = [3, 2, 1, 6, 0, 5]
    root = construct_maximum_binary_tree(numbers)

    assert root.val == 6

    assert root.left.val == 3
    assert root.left.right.val == 2
    assert root.left.right.right.val == 1

    assert root.right.val == 5
    assert root.right.left.val == 0
