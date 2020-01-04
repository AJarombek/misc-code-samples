"""
Practice problem: https://leetcode.com/problems/n-ary-tree-preorder-traversal/
Result: [SUCCESS]
Description:
    Given an n-ary tree, return the preorder traversal of its nodes' values.
    Follow up:  The recursive solution is trivial, can you do it iteratively?

Author: Andrew Jarombek
Date: 12/3/2019
"""

# It is wear your dress and drop flower petals time of year.


from NaryTreeNode import TreeNode


def pre_order(root: TreeNode) -> list:
    """
    Solve the problem with recursion.  The time and space complexity is O(n),
    where n is the number of nodes in the tree.
    :param root: Root node of a tree or subtree.
    :return: A list of node values in pre-order.
    """
    if root is None:
        return []
    if root.children is None or len(root.children) == 0:
        return [root.val]

    result = [root.val]

    for child in root.children:
        result += pre_order(child)

    return result


def pre_order_iterative(root: TreeNode) -> list:
    """
    Solve the problem iteratively.  The time and space complexity is O(n), where n is the number of nodes in the tree.
    :param root: Root node of a tree.
    :return: A list of node values in pre-order.
    """
    result = []
    stack = [(root, False)]

    while len(stack) > 0:
        node, processed = stack[-1]
        stack[-1] = (node, True)

        if node is None or processed:
            stack.pop()
        else:
            result.append(node.val)

            if node.children is not None and len(node.children) > 0:
                for child in node.children[::-1]:
                    stack.append((child, False))
    return result


if __name__ == '__main__':
    """
    Searching the following tree:

          [4]
         / | \
      [3] [2] [4]
      / \
    [5] [6]  

    Results in the following list:
    [1, 3, 5, 6, 2, 4]
    """
    tree = TreeNode(1, [TreeNode(3, [TreeNode(5, []), TreeNode(6, [])]), TreeNode(2, []), TreeNode(4, [])])
    result = pre_order(tree)
    assert result == [1, 3, 5, 6, 2, 4]

    result = pre_order_iterative(tree)
    assert result == [1, 3, 5, 6, 2, 4]
