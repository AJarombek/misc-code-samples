package routebetweennodes;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Unit Testing: Cracking the Coding Interview: Question 4.1
 * @author Andrew Jarombek
 * @since 1/7/2020
 */

public class RouteBetweenNodesTest {

    private static DirectedNode<Integer> nodeA;
    private static DirectedNode<Integer> nodeB;

    /**
     * Test the following graph:
     *
     * [1] -> [2]
     *         |
     *         v
     * [4] <- [3]
     */
    @BeforeAll
    static void init() {
        DirectedNode<Integer> node4 = new DirectedNode<>(4);
        DirectedNode<Integer> node3 = new DirectedNode<>(3, List.of(node4));
        DirectedNode<Integer> node2 = new DirectedNode<>(2, List.of(node3));
        DirectedNode<Integer> node1 = new DirectedNode<>(1, List.of(node2));

        DirectedGraph<Integer> graph = new DirectedGraph<>(List.of(node1, node2, node3, node4));
        nodeA = node1;
        nodeB = node4;
    }

    @Test
    void testAtoB() {
        assertTrue(RouteBetweenNodes.routeExists(nodeA, nodeB));
    }

    @Test
    void testBtoA() {
        assertFalse(RouteBetweenNodes.routeExists(nodeB, nodeA));
    }
}
