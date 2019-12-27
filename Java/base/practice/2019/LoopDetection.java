import linkedlist.BasicSinglyLinkedList;
import linkedlist.Node;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Cracking the Coding Interview: Question 2.8
 * Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
 * @author Andrew Jarombek
 * @since 12/26/2019
 */

public class LoopDetection {

    /**
     * Solve the problem with a custom linked list.  The time complexity is O(n^2) and the space complexity is O(1).
     * @param list The linked list which may contain a loop/cycle.
     * @param <T> The generic type of the linked list.
     * @return If there is a loop, the {@code Node<T>} object that starts the loop.  {@code null} otherwise.
     */
    private static <T> Node<T> findLoopBeginning(BasicSinglyLinkedList<T> list) {
        int index = 0;
        Node<T> current = list.first;

        while (current != null) {
            Node<T> next = current.next;

            if (next != null) {
                Node<T> node = list.first;
                for (int i = 0; i < index; i++) {
                    if (node == next) {
                        System.out.println(node);
                        return node;
                    }

                    node = node.next;
                }
            }

            current = next;
            index++;
        }

        return null;
    }

    public static void main(String[] args) {
        try {
            URL url1 = new URL("https://jarombek.com");
            URL url2 = new URL("https://saintsxctf.com");
            URL url3 = new URL("https://jarombek.io");

            // Test a list without a loop.
            BasicSinglyLinkedList<URL> list1 = new BasicSinglyLinkedList<>();
            list1.first = new Node<>(url1, new Node<>(url2, new Node<>(url3)));

            Node<URL> result1 = findLoopBeginning(list1);
            assert result1 == null;

            // Test a list with a loop.
            BasicSinglyLinkedList<URL> list2 = new BasicSinglyLinkedList<>();
            list2.first = new Node<>(url1);
            list2.first.next = new Node<>(url2);
            list2.first.next.next = new Node<>(url3);
            list2.first.next.next.next = list2.first.next;

            Node<URL> result2 = findLoopBeginning(list2);
            assert result2 != null;
            assert result2.data == url2;
        } catch (MalformedURLException e) {
            assert false;
        }
    }
}
