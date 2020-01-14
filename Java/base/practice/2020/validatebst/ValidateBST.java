package validatebst;

import tree.TreeNode;

/**
 * Cracking the Coding Interview: Question 4.5
 * Implement a function to check if a binary tree is a binary search tree.
 * Assumption: The contents of the binary tree are integers.
 * @author Andrew Jarombek
 * @since 1/14/2020
 */

class ValidateBST {

    private static Integer prevValue;

    /**
     * Solve the problem using In-Order tree traversal and a static variable containing the previous value looked at.
     * @param root The root node of the binary tree.
     * @return {@code true} if the binary tree is a binary search tree, {@code false} otherwise.
     */
    static boolean isBST(TreeNode<Integer> root) {
        prevValue = Integer.MIN_VALUE;
        return inOrderSearch(root);
    }

    /**
     * Perform a in-order traversal of the binary tree, making sure that nodes are in order along the way.  This
     * function is recursive, so the goal is to propagate up indications of false binary search subtrees up to the root.
     * @param root The root node of the binary tree (or sub-tree).
     * @return {@code true} if the tree or subtree has values in order, {@code false} otherwise.
     */
    private static boolean inOrderSearch(TreeNode<Integer> root) {
        boolean leftIsBST = true;
        boolean rightIsBST = true;

        if (root.left != null) {
            leftIsBST = inOrderSearch(root.left);
        }

        if (!leftIsBST || root.value <= prevValue) {
            return false;
        } else {
            prevValue = root.value;
        }

        if (root.right != null) {
            rightIsBST = inOrderSearch(root.right);
        }

        return rightIsBST;
    }
}
