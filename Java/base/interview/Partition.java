import linkedlist.BasicSinglyLinkedList;
import linkedlist.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Cracking the Coding Interview: Question 2.4
 * Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes
 * greater than or equal to x.  If x is contained within the list, the values of x only need to be after the elements
 * less than x.  The partition element x can appear anywhere in the "right partition"; it does not need to appear
 * between the left and right partitions.
 *
 * Example:
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
 *
 * @author Andrew Jarombek
 * @since 12/20/2019
 */

public class Partition {

    /**
     * Solve the problem with the built-in Collections framework linked list.  The time and space complexity is O(n).
     * @param list The linked list which will be modified in-place.
     * @param value The value to use as a partition in the list.
     * @param <T> The generic type of the linked list, which must contain comparable values.
     */
    private static <T extends Comparable<T>> void partitionBuiltIn(LinkedList<T> list, T value) {
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            if (item.compareTo(value) < 0) {
                list.remove(i);
                list.addFirst(item);
            }
        }
    }

    /**
     * Solve the problem with my custom linked list.  The time and space complexity is O(n).
     * @param list The linked list which will be modified in-place.
     * @param value The value to use as a partition in the list.
     * @param <T> The generic type of the linked list, which must contain comparable values.
     */
    private static <T extends Comparable<T>> void partitionCustom(BasicSinglyLinkedList<T> list, T value) {
        Node<T> first = list.first;
        Node<T> current = first;
        Node<T> prev = null;

        while (current != null) {
            if (current.data.compareTo(value) < 0 && prev != null) {

                // Create a new node which will be prepended to the beginning of the list.
                Node<T> newNode = new Node<>(current.data);

                // The 'next' instance variable of the new node is the old first node of the list.
                newNode.next = first;

                // Complete the process of prepending the newNode by assigning it to the 'first' instance variable
                // of the list.
                list.first = newNode;

                // The 'first' local variable must also be updated with the new Node<T> instance.
                first = list.first;

                // By setting prev.next equal to current.next, current is removed from the list and
                // will be garbage collected.
                prev.next = current.next;
            } else {
                prev = current;
            }

            current = current.next;
        }
    }

    public static void main(String[] args) {
        // Test the custom Linked List which consists of Nodes linked by a 'next' instance variable.
        BasicSinglyLinkedList<Integer> linkedList = new BasicSinglyLinkedList<>();
        linkedList.first = new Node<>(3, new Node<>(5, new Node<>(8, new Node<>(5, new Node<>(10,
                new Node<>(2, new Node<>(1)))))));

        assert linkedList.first.data == 3 &&
                linkedList.first.next.data == 5 &&
                linkedList.first.next.next.data == 8 &&
                linkedList.first.next.next.next.data == 5 &&
                linkedList.first.next.next.next.next.data == 10 &&
                linkedList.first.next.next.next.next.next.data == 2 &&
                linkedList.first.next.next.next.next.next.next.data == 1 &&
                linkedList.first.next.next.next.next.next.next.next == null;

        partitionCustom(linkedList, 5);

        assert linkedList.first.data == 1 &&
                linkedList.first.next.data == 2 &&
                linkedList.first.next.next.data == 3 &&
                linkedList.first.next.next.next.data == 5 &&
                linkedList.first.next.next.next.next.data == 8 &&
                linkedList.first.next.next.next.next.next.data == 5 &&
                linkedList.first.next.next.next.next.next.next.data == 10 &&
                linkedList.first.next.next.next.next.next.next.next == null;

        // Test the built-in Collections API LinkedList<T>.
        LinkedList<Integer> builtInLinkedList = new LinkedList<>(List.of(3, 5, 8, 5, 10, 2, 1));
        assert builtInLinkedList.toString().equals("[3, 5, 8, 5, 10, 2, 1]");

        partitionBuiltIn(builtInLinkedList, 5);

        assert builtInLinkedList.toString().equals("[1, 2, 3, 5, 8, 5, 10]");
    }
}
