package bstsequences;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Cracking the Coding Interview: Question 4.9
 * A binary search tree was created by traversing through an array from left to right and inserting each element.
 * Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
 *
 * Example:
 * Input:
 *     [2]
 *    /   \
 *  [1]   [3]
 *
 * Output:
 * {2, 1, 3}, {2, 3, 1}
 *
 * @author Andrew Jarombek
 * @since 1/23/2020
 */

class BSTSequences {

    static List<List<Integer>> depthList;
    static List<List<Integer>> sequences;

    static List<List<Integer>> possibleSequences(TreeNode<Integer> root) {
        sequences = new ArrayList<>();
        depthList = new ArrayList<>();

        createDepthList(root, 0);
        createSequences(depthList, 0);

        return sequences;
    }

    private static void createDepthList(TreeNode<Integer> root, int depth) {
        if (root != null) {
            if (depthList.size() <= depth) {
                depthList.add(new ArrayList<>());
            }

            depthList.get(depth).add(root.value);

            createDepthList(root.left, depth + 1);
            createDepthList(root.right, depth + 1);
        }
    }

    private static void createSequences(List<List<Integer>> depthList, int depth) {
        // TODO
    }

    private static List<List<Integer>> listPermutations() {
        // TODO
    }
}
