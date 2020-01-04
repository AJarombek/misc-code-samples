"""
Practice problem: https://leetcode.com/problems/n-ary-tree-postorder-traversal
Result: [SUCCESS - 0:12]
Description:
    Given an n-ary tree, return the postorder traversal of its nodes' values.  Nary-Tree input serialization is
    represented in their level order traversal, each group of children is separated by the null value (See examples).

    Follow up:  The recursive solution is trivial, can you do it iteratively?

Author: Andrew Jarombek
Date: 12/2/2019
"""


from NaryTreeNode import TreeNode


def post_order(root: TreeNode) -> list:
    """
    Solve the problem with recursion.  The time and space complexity is O(n),
    where n is the number of nodes in the tree.
    :param root: Root node of a tree or subtree.
    :return: A list of node values in post-order.
    """
    result = []
    if root is None:
        return result
    if root.children is None or len(root.children) == 0:
        return [root.val]

    for child in root.children:
        result += post_order(child)

    result.append(root.val)
    return result


def post_order_iterative(root: TreeNode) -> list:
    """
    Solve the problem iteratively.  The time and space complexity is O(n), where n is the number of nodes in the tree.
    :param root: Root node of a tree.
    :return: A list of node values in post-order.
    """
    result = []
    stack = [(root, False)]

    while len(stack) > 0:
        node, processed = stack[-1]
        stack[-1] = (node, True)

        if node is None:
            stack.pop()
        elif node.children is not None and len(node.children) > 0 and not processed:
            for child in node.children[::-1]:
                stack.append((child, False))
        else:
            result.append(node.val)
            stack.pop()
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
    [5, 6, 3, 2, 4, 1]
    """
    tree = TreeNode(1, [TreeNode(3, [TreeNode(5, []), TreeNode(6, [])]), TreeNode(2, []), TreeNode(4, [])])
    result = post_order(tree)
    assert result == [5, 6, 3, 2, 4, 1]

    result = post_order_iterative(tree)
    assert result == [5, 6, 3, 2, 4, 1]
