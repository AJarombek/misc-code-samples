import linkedlist.BasicSinglyLinkedList;
import linkedlist.Node;

/**
 * Cracking the Coding Interview: Question 2.3
 * Implement an algorithm to delete a node in the middle (i.e. any node but the first and last node, not necessarily
 * the exact middle) of a singly linked list, given only access to that node.
 * On the metro north train to the track meet.  Stopping at the disney store for family XMas gifts :)
 * @author Andrew Jarombek
 * @since 12/19/2019
 */

public class DeleteMiddleNode {

    /**
     * Solve the problem by setting the values of nodes to the value of node.next.  The time complexity is O(n)
     * [although it will approach but never reach n] and the space complexity is O(1), since the existing Node<T>
     * objects are reused.
     * @param middleNode A node in the middle of a singly linked list.
     * @param <T> The generic type of the node in the linked list.
     */
    private static <T> void deleteMiddle(Node<T> middleNode) {
        while (middleNode != null) {
            Node<T> next = middleNode.next;

            if (next != null) {
                middleNode.data = middleNode.next.data;

                if (next.next == null) {
                    middleNode.next = null;
                }

                middleNode = middleNode.next;
            }
        }
    }

    public static void main(String[] args) {
        BasicSinglyLinkedList<Integer> linkedList = new BasicSinglyLinkedList<>();
        linkedList.first = new Node<>(1, new Node<>(2, new Node<>(3, new Node<>(4, new Node<>(5)))));

        assert linkedList.first.data == 1 &&
                linkedList.first.next.data == 2 &&
                linkedList.first.next.next.data == 3 &&
                linkedList.first.next.next.next.data == 4 &&
                linkedList.first.next.next.next.next.data == 5 &&
                linkedList.first.next.next.next.next.next == null;

        deleteMiddle(linkedList.first.next.next);

        assert linkedList.first.data == 1 &&
                linkedList.first.next.data == 2 &&
                linkedList.first.next.next.data == 4 &&
                linkedList.first.next.next.next.data == 5 &&
                linkedList.first.next.next.next.next == null;
    }
}
