package routebetweennodes;

import java.util.ArrayList;
import java.util.List;

/**
 * A node within a directed graph.
 * @author Andrew Jarombek
 * @since 1/7/2020
 */

public class DirectedNode<T> {

    T item;
    List<DirectedNode<T>> neighbors;

    /**
     * Construct a directed node without any neighbors.
     * @param item The value of the node.
     */
    DirectedNode(T item) {
        this(item, null);
    }

    /**
     * Construct a directed node with a list of neighbor nodes.
     * @param item The value of the node.
     * @param neighbors Any data structure that implements the {@code Iterable} interface.  Converted into a list
     *                 of neighbors.
     */
    DirectedNode(T item, Iterable<DirectedNode<T>> neighbors) {
        this.item = item;
        this.neighbors = new ArrayList<>();

        if (neighbors != null) {
            neighbors.forEach(this.neighbors::add);
        }
    }
}
