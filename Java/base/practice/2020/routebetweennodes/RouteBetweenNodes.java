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

    static <T> boolean routeExists(DirectedNode<T> node1, DirectedNode<T> node2) {
        Set<DirectedNode<T>> visited = new HashSet<>();
        Queue<DirectedNode<T>> nodeQueue = new LinkedBlockingQueue<>();

        nodeQueue.add(node1);

        while (!nodeQueue.isEmpty()) {
            DirectedNode<T> currentNode = nodeQueue.poll();
            visited.add(currentNode);

            for (DirectedNode<T> neighborNode : currentNode.neighbors) {
                if (neighborNode == node2) {
                    return true;
                } else if (!visited.contains(neighborNode) && neighborNode.neighbors != null) {
                    nodeQueue.add(neighborNode);
                }
            }
        }

        return false;
    }
}
