"""
Find the distance between two binary tree nodes.
Author: Andrew Jarombek
Date: 12/10/2019
"""

from collections import deque
from TreeNode import TreeNode


def distance_between_nodes_binary_tree(root: TreeNode, val1: int, val2: int) -> int:
    pass


def distance_between_nodes_binary_search_tree(root: TreeNode, val1: int, val2: int) -> int:
    pass


def lowest_common_ancestor_binary_tree(root: TreeNode, val1: int, val2: int) -> tuple:
    """
    Solve the lowest common ancestor problem for a binary tree.  The approach is given here and the
    implementation is my own: https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
    :param root: Root node of a binary tree (or subtree).
    :param val1: The first value to look for a common ancestor.
    :param val2: The second value to look for a common ancestor.
    :return: A tuple containing the common ancestor's value and its depth in the tree.
    """
    val1_path: deque = lowest_common_ancestor_binary_tree_path(root, val1)
    val2_path: deque = lowest_common_ancestor_binary_tree_path(root, val2)
    print(val1_path)
    print(val2_path)

    lca = None
    lca_index = None

    if val1_path is None or val2_path is None:
        return None

    for i in range(min(len(val1_path), len(val2_path))):
        ancestor1 = val1_path.pop()
        ancestor2 = val2_path.pop()
        if ancestor1 == ancestor2:
            lca = ancestor1
            lca_index = i

    return lca, lca_index


def lowest_common_ancestor_binary_tree_path(root: TreeNode, val: int) -> deque:
    """
    Helper function to create a deque containing the path in a binary tree from the root node to another node with a
    given value.
    :param root: The root node of a binary tree (or subtree).
    :param val: The value to find a path towards.
    :return: A deque (double ended queue) containing the path within the tree.
    """
    if root is not None and root.val == val:
        path = deque()
        path.append(root.val)
        return path
    else:
        if root is None or root.val is None:
            return None
        else:
            left_path = lowest_common_ancestor_binary_tree_path(root.left, val)

            if left_path is not None:
                left_path.append(root.val)
                return left_path

            right_path = lowest_common_ancestor_binary_tree_path(root.right, val)

            if right_path is not None:
                right_path.append(root.val)
                return right_path

            return None


def lowest_common_ancestor_binary_search_tree(root: TreeNode, val1: int, val2: int, dist: int = 0) -> tuple:
    """
    Solve the lowest common ancestor problem for a binary search tree.  The approach is given here and the
    implementation is my own: https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
    :param root: Root node of a binary search tree (or subtree).
    :param val1: The first (smaller) value to look for a common ancestor.
    :param val2: The second (larger) value to look for a common ancestor.
    :param dist: The depth (distance from the root node) currently in the binary search tree.
    :return: A tuple containing the common ancestor's value and its depth in the tree.
    """
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
    # Test the lowest common ancestor in a binary search tree problem.
    tree1 = TreeNode(2, TreeNode(1), TreeNode(3))
    result1 = lowest_common_ancestor_binary_search_tree(root=tree1, val1=1, val2=3)
    assert result1 == (2, 0)

    tree2 = TreeNode(8, TreeNode(3, TreeNode(2), TreeNode(6, TreeNode(4), TreeNode(7))), TreeNode(9))
    result2a = lowest_common_ancestor_binary_search_tree(root=tree2, val1=4, val2=7)
    assert result2a == (6, 2)

    result2b = lowest_common_ancestor_binary_search_tree(root=tree2, val1=6, val2=9)
    assert result2b == (8, 0)

    # Test the lowest common ancestor in a binary tree problem.
    tree3 = TreeNode(3, TreeNode(7), TreeNode(10))
    result3 = lowest_common_ancestor_binary_tree(root=tree3, val1=7, val2=10)
    assert result3 == (3, 0)

    tree4 = TreeNode(8, TreeNode(3, TreeNode(2), TreeNode(6, TreeNode(4), TreeNode(7))), TreeNode(9))
    result4a = lowest_common_ancestor_binary_tree(root=tree4, val1=4, val2=7)
    assert result4a == (6, 2)

    result4b = lowest_common_ancestor_binary_tree(root=tree4, val1=6, val2=9)
    assert result4b == (8, 0)

    result4c = lowest_common_ancestor_binary_tree(root=tree4, val1=7, val2=11)
    assert result4c is None
