import linkedlist.BasicSinglyLinkedList;
import linkedlist.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Cracking the Coding Interview: Question 2.2
 * Implement an algorithm to find the kth to last element of a singly linked list.
 * @author Andrew Jarombek
 * @since 12/18/2019
 */

public class ReturnKthToLast {

    private static int index;

    /**
     * Solve the problem using the built-in Collections library linked list data structure.  This implementation
     * is trivial.
     * @param linkedList A linked list to access an item from at the given index.
     * @param kToLast The k-th to last index in the list.
     * @param <T> The generic type of the linked list elements.
     * @return The value at the k-th to last index in the list.
     */
    private static <T> T getElementBuiltIn(LinkedList<T> linkedList, int kToLast) {
        int index = linkedList.size() - kToLast;
        return linkedList.get(index);
    }

    /**
     * Solve the problem using the custom linked list data structure.  The time complexity is O(n) and the space
     * complexity is O(1).
     * @param linkedList A linked list to access an item from at the given index.
     * @param kToLast The k-th to last index in the list.
     * @param <T> The generic type of the linked list elements.
     * @return The value at the k-th to last index in the list.
     */
    private static <T> T getElementCustom(BasicSinglyLinkedList<T> linkedList, int kToLast) {
        Node<T> current = linkedList.first;
        int length = 0;

        while (current != null) {
            length++;
            current = current.next;
        }

        int index = length - kToLast;
        current = linkedList.first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    /**
     * Solve the problem using a custom linked list data structure and recursion.  The time complexity and space
     * complexity is O(n).
     * @param linkedList A linked list to access an item from at the given index.
     * @param kToLast The k-th to last index in the list.
     * @param <T> The generic type of the linked list elements.
     * @return The value at the k-th to last index in the list.
     */
    private static <T> T getElementCustomRecursive(BasicSinglyLinkedList<T> linkedList, int kToLast) {
        return getElementCustomRecursive(linkedList.first, kToLast);
    }

    /**
     * Helper function for {@link ReturnKthToLast#getElementCustomRecursive(BasicSinglyLinkedList, int)} that
     * performs the recursive logic.
     * @param node A node in a singly linked list.
     * @param kToLast The k-th to last index in the list.
     * @param <T> The generic type of the linked list node values.
     * @return The value at the k-th to last index in the list.
     */
    private static <T> T getElementCustomRecursive(Node<T> node, int kToLast) {
        T result = null;
        if (node != null) {
            result = getElementCustomRecursive(node.next, kToLast);

            index++;
            if (kToLast == index) {
                return node.data;
            }
        } else {
            index = 0;
        }

        return result;
    }

    public static void main(String[] args) {
        BasicSinglyLinkedList<Integer> linkedList = new BasicSinglyLinkedList<>();
        linkedList.first = new Node<>(1, new Node<>(2, new Node<>(3, new Node<>(4, new Node<>(5,
                new Node<>(6, new Node<>(7, new Node<>(8, new Node<>(9)))))))));

        int result = getElementCustom(linkedList, 3);
        assert result == 7;

        int resultRecursive = getElementCustomRecursive(linkedList, 3);
        assert resultRecursive == 7;

        LinkedList<Integer> builtInLinkedList = new LinkedList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        int builtInResult = getElementBuiltIn(builtInLinkedList, 3);
        assert builtInResult == 7;
    }
}
