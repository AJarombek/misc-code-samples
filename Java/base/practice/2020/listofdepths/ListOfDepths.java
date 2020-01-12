package listofdepths;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Cracking the Coding Interview: Question 4.3
 * Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth (eg. If you have
 * a tree with depth D, you'll have D linked lists).
 * @author Andrew Jarombek
 * @since 1/12/2020
 */

public class ListOfDepths {

    /**
     * Solve the problem using a helper function and DFS implemented with recursion.
     * @param root The root node in the binary tree.
     * @param <T> The generic type of the binary tree.
     * @return A linked list containing all the nodes at each depth.
     */
    static <T> List<List<TreeNode<T>>> dfs(TreeNode<T> root) {
        return dfs(root, new ArrayList<>(), 0);
    }

    /**
     * The helper function to solve the problem recursively.
     * @param root The root node in the binary tree.
     * @param depthList A reference to the linked list that needs to be populated with nodes.
     * @param depth The current recursive depth which corresponds to the depth of the root argument in the binary tree.
     * @param <T> The generic type of the binary tree.
     * @return A linked list containing all the nodes at each depth.
     */
    private static <T> List<List<TreeNode<T>>> dfs(TreeNode<T> root, List<List<TreeNode<T>>> depthList, int depth) {
        if (depthList.size() <= depth) {
            depthList.add(new LinkedList<>());
        }

        depthList.get(depth).add(root);

        if (root.left != null) {
            dfs(root.left, depthList, depth + 1);
        }
        if (root.right != null) {
            dfs(root.right, depthList, depth + 1);
        }

        return depthList;
    }
}
