package tree;

/**
 * Representation of a node within a tree.  Can be used in a binary tree or binary search tree.
 * @author Andrew Jarombek
 * @since 1/8/2020
 */

// Got a bit of a cold skiing, so unfortunately im not racing the 3k tomorrow.  Excited to race next week though!

public class TreeNode<T> {
    public T value;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T value) {
        this(value, null, null);
    }

    public TreeNode(T value, TreeNode<T> left, TreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
