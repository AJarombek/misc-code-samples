package successor;

import tree.TreeNodeParentRef;

/**
 * Cracking the Coding Interview: Question 4.6
 * Write an algorithm to find the "next" node (i.e. in-order successor) of a given node in a binary search tree.  You
 * may assume that each node has a link to its parent.
 * Assumption: The binary search tree contains integers.
 * @author Andrew Jarombek
 * @since 1/15/2020
 */

class Successor {

    /**
     * Solve the problem with a while loop and the conditions for a successor in a binary search tree.
     * @param node The node to find the successor for in the binary search tree.
     * @return The successor node in the binary search tree.
     */
    static TreeNodeParentRef<Integer> findSuccessor(TreeNodeParentRef<Integer> node) {
        TreeNodeParentRef<Integer> parent = node;
        while (parent != null) {
            if (parent.left == node) {
                return parent;
            } else if (parent.right != null && parent.right.value > node.value) {
                return parent.right;
            } else {
                parent = parent.parent;
            }
        }

        return null;
    }
}
