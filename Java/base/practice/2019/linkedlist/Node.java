package linkedlist;

/**
 * A node in a singly linked list (each node has a 'next' instance variable).
 * @author Andrew Jarombek
 * @since 12/18/2019
 */

public class Node<T> {

    public T data;
    public Node<T> next;

    /**
     * Construct a new node without any 'next' node linked to it.
     * @param data The data that the node holds.
     */
    public Node(T data) {
        this(data, null);
    }

    /**
     * Construct a new node with another node linked to it.
     * @param data The data that the node holds.
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }
}
