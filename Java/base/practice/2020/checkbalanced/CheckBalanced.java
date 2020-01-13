package checkbalanced;

import tree.TreeNode;

/**
 * Cracking the Coding Interview: Question 4.4
 * Implement a function to check if a binary tree is balanced.  For the purposes of this question, a balanced tree is
 * defined to be a tree such that the heights of the two subtrees of any node never differ by more than one.
 * @author Andrew Jarombek
 * @since 1/13/2020
 */

class CheckBalanced {

    /**
     * Determine if a binary tree is balanced based on the requirements specified in the above problem statement.
     * @param root The root node in the binary tree.
     * @param <T> Generic type of the binary tree.
     * @return {@code true} if the binary tree is balanced, {@code false} otherwise.
     */
    static <T> boolean isBalanced(TreeNode<T> root) {
        if (root == null) {
            return true;
        }

        int leftHeight = treeHeight(root.left);
        int rightHeight = treeHeight(root.right);

        if (!isBalanced(root.left) || !isBalanced(root.right)) {
            return false;
        }

        return leftHeight - rightHeight <= 1 && leftHeight - rightHeight >= -1;
    }

    /**
     * Compute the height of a tree.  This is a helper function for determining if a binary tree is balanced.
     * @param root Root node in the tree.
     * @param <T> The generic type of the binary tree.
     * @return The height of the binary tree.
     */
    private static <T> int treeHeight(TreeNode<T> root) {
        if (root != null) {
            int childHeight = Math.max(treeHeight(root.left), treeHeight(root.right));
            return childHeight + 1;
        } else {
            return 0;
        }
    }
}
