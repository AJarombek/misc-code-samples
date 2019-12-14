"""
Find the distance between two binary tree nodes.
Author: Andrew Jarombek
Date: 12/10/2019
"""

from collections import deque
from TreeNode import TreeNode


def distance_between_nodes_binary_tree(root: TreeNode, val1: int, val2: int) -> int:
    """
    Solve the distance between binary tree nodes problem by utilizing the dfs and lowest common ancestor methods.
    :param root: Root node of a binary tree.
    :param val1: The first value that exists in the binary tree.
    :param val2: The second value that exists in the binary tree.
    :return: The distance between the two nodes in the binary tree.
    """
    val1_depth = dfs_binary_tree(root, val1)
    val2_depth = dfs_binary_tree(root, val2)
    _, lca_depth = lowest_common_ancestor_binary_tree(root, val1, val2)

    if val1_depth is not None and val2_depth is not None and lca_depth is not None:
        return (val1_depth - lca_depth) + (val2_depth - lca_depth)
    else:
        return None


def distance_between_nodes_binary_search_tree(root: TreeNode, val1: int, val2: int) -> int:
    """
    Solve the distance between binary search tree nodes problem by utilizing the dfs and lowest common ancestor methods.
    :param root: Root node of a binary search tree.
    :param val1: The first value that exists in the binary search tree.
    :param val2: The second value that exists in the binary search tree.
    :return: The distance between the two nodes in the binary search tree.
    """
    val1_depth = dfs_binary_search_tree(root, val1)
    val2_depth = dfs_binary_search_tree(root, val2)
    _, lca_depth = lowest_common_ancestor_binary_search_tree(root, val1, val2)

    if val1_depth is not None and val2_depth is not None and lca_depth is not None:
        return (val1_depth - lca_depth) + (val2_depth - lca_depth)
    else:
        return None


def dfs_binary_tree(root: TreeNode, val: int, depth: int = 0) -> int:
    """
    DFS (depth first search) for a binary tree.
    :param root: The root node of a binary tree (or subtree).
    :param val: A value to find in the binary tree.
    :param depth: Current depth in the binary tree.
    :return: The depth of the specified value in the binary tree.
    """
    if root is None or root.val is None:
        return None
    if root.val == val:
        return depth

    left = dfs_binary_tree(root.left, val, depth + 1)

    if left is not None:
        return left

    right = dfs_binary_tree(root.right, val, depth + 1)

    if right is not None:
        return right

    return None


def dfs_binary_search_tree(root: TreeNode, val: int, depth: int = 0) -> int:
    """
    DFS (depth first search) for a binary search tree.
    :param root: The root node of a binary search tree (or subtree).
    :param val: A value to find in the binary search tree.
    :param depth: Current depth in the binary search tree.
    :return: The depth of the specified value in the binary search tree.
    """
    if root is None or root.val is None:
        return None
    if root.val == val:
        return depth

    if val < root.val:
        return dfs_binary_search_tree(root.left, val, depth + 1)
    else:
        return dfs_binary_search_tree(root.right, val, depth + 1)


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

    dist1 = distance_between_nodes_binary_search_tree(root=tree1, val1=1, val2=3)
    assert dist1 == 2

    tree2 = TreeNode(8, TreeNode(3, TreeNode(2), TreeNode(6, TreeNode(4), TreeNode(7))), TreeNode(9))
    result2a = lowest_common_ancestor_binary_search_tree(root=tree2, val1=4, val2=7)
    assert result2a == (6, 2)

    dist2a = distance_between_nodes_binary_search_tree(root=tree2, val1=4, val2=7)
    assert dist2a == 2

    result2b = lowest_common_ancestor_binary_search_tree(root=tree2, val1=6, val2=9)
    assert result2b == (8, 0)

    dist2b = distance_between_nodes_binary_search_tree(root=tree2, val1=6, val2=9)
    assert dist2b == 3

    # Test the lowest common ancestor in a binary tree problem.
    tree3 = TreeNode(3, TreeNode(7), TreeNode(10))
    result3 = lowest_common_ancestor_binary_tree(root=tree3, val1=7, val2=10)
    assert result3 == (3, 0)

    dist3 = distance_between_nodes_binary_tree(root=tree3, val1=7, val2=10)
    assert dist3 == 2

    tree4 = TreeNode(8, TreeNode(3, TreeNode(2), TreeNode(6, TreeNode(4), TreeNode(7))), TreeNode(9))
    result4a = lowest_common_ancestor_binary_tree(root=tree4, val1=4, val2=7)
    assert result4a == (6, 2)

    dist4a = distance_between_nodes_binary_tree(root=tree4, val1=4, val2=7)
    assert dist4a == 2

    result4b = lowest_common_ancestor_binary_tree(root=tree4, val1=6, val2=9)
    assert result4b == (8, 0)

    dist4b = distance_between_nodes_binary_tree(root=tree4, val1=6, val2=9)
    assert dist4b == 3

    result4c = lowest_common_ancestor_binary_tree(root=tree4, val1=7, val2=11)
    assert result4c is None
