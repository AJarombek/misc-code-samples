"""
Interview practice problem: https://leetcode.com/problems/all-possible-full-binary-trees/.
Result: [FAILED - 1:25] -- I failed to come up with the proper recursive logic for this problem.  However, I learned
    a bit about Memoization, full and complete binary trees, and Python's history of xrange() and range() along the way.
Description:
    A full binary tree is a binary tree where each node has exactly 0 or 2 children.  Return a list of all possible full
    binary trees with N nodes.  Each element of the answer is the root node of one possible tree.  Each node of each
    tree in the answer must have node.val = 0.

Author: Andrew Jarombek
Date: 11/4/2019
"""

from TreeNode import TreeNode


class FullBinaryTree:
    # Use Memoization to cache results from expensive operations
    memo = {0: [], 1: [TreeNode(0, None, None)]}

    def all_possible_fbt(self, n: int) -> list:
        """
        Since I failed to come up with an answer, this is based of the following solution:
        https://leetcode.com/problems/all-possible-full-binary-trees/solution/
        :param n: The number of nodes in the full binary tree.
        :return: All the possible full binary trees with 'n' number of nodes.
        """
        if n not in self.memo:
            result = []

            for i in range(n):
                j = n - 1 - i

                for left in self.all_possible_fbt(i):
                    for right in self.all_possible_fbt(j):
                        tree = TreeNode(0, None, None)
                        tree.left = left
                        tree.right = right
                        result.append(tree)
            self.memo[n] = result

        return self.memo[n]


if __name__ == '__main__':
    fbt = FullBinaryTree()

    result1 = fbt.all_possible_fbt(1)
    print(result1[0].val)
    assert len(result1) == 1  # [TreeNode(0, None, None)]

    result2 = fbt.all_possible_fbt(2)
    assert result2 == []

    result3 = fbt.all_possible_fbt(3)
    assert len(result3) == 1  # [TreeNode(0, TreeNode(0, None, None), TreeNode(0, None, None))]
