package firstcommonanc;

import tree.TreeNode;

/**
 * Cracking the Coding Interview: Question 4.8
 * Design an algorithm to find the first common ancestor of two nodes in a binary tree.  Avoid storing additional nodes
 * in a data structure.  NOTE: This is not necessarily a binary search tree.
 *
 * @author Andrew Jarombek
 * @since 1/21/2020
 */

class FirstCommonAncestor {

    private static Found found = Found.NONE;

    enum Found {
        NONE, FIRST, SECOND
    }

    /**
     * Solve the common ancestor problem by first searching for the first node recursively, and then performing a DFS
     * for the second node from that node and all its parents.
     * @param root The root node of the binary tree.
     * @param node1 The first node to search for.
     * @param node2 The second node to search for.
     * @param <T> The generic type of the binary tree.
     * @return The common ancestor node or null if one of the nodes does not exist.
     */
    static <T> TreeNode<T> commonAncestor(TreeNode<T> root, TreeNode<T> node1, TreeNode<T> node2) {
        found = Found.NONE;
        return commonAncestorSearch(root, node1, node2);
    }

    private static <T> TreeNode<T> commonAncestorSearch(TreeNode<T> root, TreeNode<T> node1, TreeNode<T> node2) {
        if (found == Found.NONE) {
            if (root == node1) {
                found = Found.FIRST;
            } else if (root == node2) {
                found = Found.SECOND;
            } else {
                TreeNode<T> leftSearch = commonAncestorSearch(root.left, node1, node2);

                if (leftSearch != null) {
                    return leftSearch;
                }

                if (found == Found.NONE) {
                    TreeNode<T> rightSearch = commonAncestorSearch(root.right, node1, node2);

                    if (rightSearch != null) {
                        return rightSearch;
                    }
                }
            }
        }

        if (found != Found.NONE) {
            TreeNode<T> foundNode = (found == Found.FIRST) ? dfs(root, node2) : dfs(root, node1);
            return (foundNode != null) ? root : null;
        }

        return null;
    }

    /**
     * Perform a Depth First Search on a binary tree.  This helper function is used once the first target node is found.
     * This is a recursive search.
     * @param root The root node of the tree or subtree.
     * @param target The node to search for in the tree or subtree.
     * @param <T> The generic type of the tree node values.
     * @return The target node if it is found, {@code null} otherwise.
     */
    private static <T> TreeNode<T> dfs(TreeNode<T> root, TreeNode<T> target) {
        if (root == null) {
            return null;
        } else if (root == target) {
            return root;
        } else {
            TreeNode<T> leftSearch = dfs(root.left, target);
            TreeNode<T> rightSearch = dfs(root.right, target);
            return (leftSearch != null) ? leftSearch : rightSearch;
        }
    }
}
