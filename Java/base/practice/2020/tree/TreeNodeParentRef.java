package tree;

/**
 * Representation of a node in a binary tree that also has a back reference to its parent node.
 * @author Andrew Jarombek
 * @since 1/15/2020
 */

public class TreeNodeParentRef<T> {
    public T value;
    public TreeNodeParentRef<T> left;
    public TreeNodeParentRef<T> right;
    public TreeNodeParentRef<T> parent;

    public TreeNodeParentRef(T value) {
        this(value, null, null, null);
    }

    public TreeNodeParentRef(T value, TreeNodeParentRef<T> parent) {
        this(value, parent, null, null);
    }

    public TreeNodeParentRef(T value, TreeNodeParentRef<T> left, TreeNodeParentRef<T> right) {
        this(value, null, left, right);
    }

    public TreeNodeParentRef(T value, TreeNodeParentRef<T> parent, TreeNodeParentRef<T> left,
                             TreeNodeParentRef<T> right) {
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
}
