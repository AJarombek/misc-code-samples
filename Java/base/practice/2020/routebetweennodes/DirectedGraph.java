package routebetweennodes;

import java.util.ArrayList;
import java.util.List;

/**
 * A directed graph that consists of a list of nodes.  Each node holds references to child nodes it has a directed
 * edge towards.
 * @author Andrew Jarombek
 * @since 1/7/2020
 */

class DirectedGraph<T> {
    List<DirectedNode<T>> nodes;

    /**
     * Construct a directed graph which is made up of a list of nodes.  Nodes contain the directed edges to neighbors.
     * @param nodes Nodes in the graph.  Is any data structure that implements {@code Iterable}.
     */
    DirectedGraph(Iterable<DirectedNode<T>> nodes) {
        this.nodes = new ArrayList<>();
        nodes.forEach(this.nodes::add);
    }
}
