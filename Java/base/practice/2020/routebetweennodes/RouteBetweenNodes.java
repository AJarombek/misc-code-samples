package routebetweennodes;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Cracking the Coding Interview: Question 4.1
 * Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
 * Had a fun day skiing.
 * @author Andrew Jarombek
 * @since 1/7/2020
 */

public class RouteBetweenNodes {

    /**
     * Solve the problem of determining if a route exists between two nodes.  The time complexity is O(n), where n
     * is the number of nodes in the graph.  The space complexity is also O(n), because a queue and visited set are
     * maintained.
     * @param startNode The starting point node in the graph.
     * @param endNode The ending point node in the graph.
     * @param <T> The generic type of the nodes in the graph.
     * @return {@code true} if there is a route between the two nodes, {@code false} otherwise.
     */
    static <T> boolean routeExists(DirectedNode<T> startNode, DirectedNode<T> endNode) {
        Set<DirectedNode<T>> visited = new HashSet<>();
        Queue<DirectedNode<T>> nodeQueue = new LinkedBlockingQueue<>();

        nodeQueue.add(startNode);

        while (!nodeQueue.isEmpty()) {
            DirectedNode<T> currentNode = nodeQueue.poll();
            visited.add(currentNode);

            for (DirectedNode<T> neighborNode : currentNode.neighbors) {
                if (neighborNode == endNode) {
                    return true;
                } else if (!visited.contains(neighborNode) && neighborNode.neighbors != null) {
                    nodeQueue.add(neighborNode);
                }
            }
        }

        return false;
    }
}
