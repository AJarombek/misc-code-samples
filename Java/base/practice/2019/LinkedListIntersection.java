import linkedlist.BasicSinglyLinkedList;
import linkedlist.Node;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Cracking the Coding Interview: Question 2.7
 * Given two (singly) linked lists, determine if the two lists intersect.  Return the intersecting node.  Note that
 * the intersection is defined based on reference, not value.  That is, if the kth node of the linked list is the exact
 * same node (by reference) as the jth node of the second linked list, then they are intersecting.
 * @author Andrew Jarombek
 * @since 12/25/2019
 */

public class LinkedListIntersection {

    /**
     * Solve the problem using the built-in Collections framework LinkedList<T>.  The time complexity is O(nm), where
     * n is the length of the first list and m is the length of the second list.  The space complexity is O(1).
     * @param list1 The first linked list.
     * @param list2 The second linked list.
     * @param <T> The generic type of the two linked lists.
     * @return {@code true} if the two lists share a common element, {@code false} otherwise.
     */
    private static <T> boolean doIntersect(LinkedList<T> list1, LinkedList<T> list2) {
        for (T item1 : list1) {
            for (T item2 : list2) {
                if (item1 == item2) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Solve the problem using a custom linked list consisting of linked {@code Node<T>} objects.  The time complexity
     * is O(nm), where n is the length of the first list and m is the length of the second list.
     * The space complexity is O(1).
     * @param list1 The first linked list.
     * @param list2 The second linked list.
     * @param <T> The generic type of the two linked lists.
     * @return {@code true} if the two lists share a common element, {@code false} otherwise.
     */
    private static <T> boolean doIntersect(BasicSinglyLinkedList<T> list1, BasicSinglyLinkedList<T> list2) {
        Node<T> current1 = list1.first;
        Node<T> current2 = list2.first;

        while (current1 != null) {
            while (current2 != null) {
                if (current1.data == current2.data) {
                    return true;
                }
                current2 = current2.next;
            }
            current1 = current1.next;
            current2 = list2.first;
        }

        return false;
    }

    public static void main(String[] args) {
        try {
            URL url1 = new URL("https://jarombek.com");
            URL url2 = new URL("https://saintsxctf.com");
            URL url3 = new URL("https://jarombek.io");

            // Test the built-in Linked List
            assert doIntersect(new LinkedList<>(List.of(url1, url2)), new LinkedList<>(List.of(url2, url3)));
            assert !doIntersect(new LinkedList<>(List.of(url1, url2)), new LinkedList<>(List.of(url3)));

            // Test the custom Linked List/Node
            BasicSinglyLinkedList<URL> list1 = new BasicSinglyLinkedList<>();
            list1.first = new Node<>(url1, new Node<>(url2));

            BasicSinglyLinkedList<URL> list2 = new BasicSinglyLinkedList<>();
            list2.first = new Node<>(url2, new Node<>(url3));

            BasicSinglyLinkedList<URL> list3 = new BasicSinglyLinkedList<>();
            list3.first = new Node<>(url3);

            assert doIntersect(list1, list2);
            assert !doIntersect(list1, list3);
        } catch (MalformedURLException e) {
            assert false;
        }
    }
}
