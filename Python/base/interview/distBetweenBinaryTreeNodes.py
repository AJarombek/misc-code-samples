"""
Find the distance between two binary tree nodes.
Author: Andrew Jarombek
Date: 12/10/2019
"""

from TreeNode import TreeNode


def distance_between_nodes(root: TreeNode, val1: int, val2: int) -> int:
    pass


def lowest_common_ancestor_binary_tree(root: TreeNode, val1: int, val2: int) -> int:
    pass


def lowest_common_ancestor_binary_search_tree(root: TreeNode, val1: int, val2: int, dist: int = 0) -> tuple:
    if root is None or root.val is None:
        return None

    if val1 <= root.val <= val2:
        return root.val, dist
    else:
        left_result = lowest_common_ancestor_binary_search_tree(root.left, val1, val2, dist + 1)
        right_result = lowest_common_ancestor_binary_search_tree(root.right, val1, val2, dist + 1)

        if left_result is not None:
            return left_result
        if right_result is not None:
            return right_result


if __name__ == '__main__':
    tree1 = TreeNode(2, TreeNode(1), TreeNode(3))
    result1 = lowest_common_ancestor_binary_search_tree(root=tree1, val1=1, val2=3)
    assert result1 == (2, 0)

    tree2 = TreeNode(8, TreeNode(3, TreeNode(2), TreeNode(6, TreeNode(4), TreeNode(7))), TreeNode(9))
    result2 = lowest_common_ancestor_binary_search_tree(root=tree2, val1=4, val2=7)
    assert result2 == (6, 2)
