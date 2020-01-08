package minimaltree;

import tree.TreeNode;

import java.util.Arrays;

/**
 * Cracking the Coding Interview: Question 4.2
 * Given a sorted (increasing order) array with unique integer elements, write an algorithm to create a binary search
 * tree with minimal height.
 * @author Andrew Jarombek
 * @since 1/8/2020
 */

public class MinimalTree {

    /**
     * Solve the problem using recursion.
     * @param sortedArray Array that will be transformed into a minimum-height binary search tree.
     * @return The root node in a binary search tree.
     */
    static TreeNode<Integer> createBinarySearchTree(int[] sortedArray) {
        if (sortedArray == null || sortedArray.length == 0) {
            return null;
        } else if (sortedArray.length == 1) {
            return new TreeNode<>(sortedArray[0], null, null);
        } else {

            int[] leftArray;
            try {
                leftArray = Arrays.copyOfRange(sortedArray, 0, sortedArray.length / 2);
            } catch (Exception ex) {
                leftArray = null;
            }

            int[] rightArray;
            try {
                rightArray = Arrays.copyOfRange(sortedArray, (sortedArray.length / 2) + 1, sortedArray.length);
            } catch (Exception ex) {
                rightArray = null;
            }

            TreeNode<Integer> left = createBinarySearchTree(leftArray);
            TreeNode<Integer> right = createBinarySearchTree(rightArray);

            return new TreeNode<>(sortedArray[sortedArray.length / 2], left, right);
        }
    }
}
