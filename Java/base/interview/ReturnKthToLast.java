import linkedlist.BasicSinglyLinkedList;
import linkedlist.Node;

import java.util.LinkedList;

/**
 * Cracking the Coding Interview: Question 2.2
 * Implement an algorithm to find the kth to last element of a singly linked list.
 * @author Andrew Jarombek
 * @since 12/18/2019
 */

public class ReturnKthToLast {

    private static <T> void getElementBuiltIn(LinkedList<T> linkedList, int kToList) {

    }

    private static <T> T getElementCustom(BasicSinglyLinkedList<T> linkedList, int kToList) {
        Node<T> current = linkedList.first;
        int length = 0;

        while (current != null) {
            length++;
            current = current.next;
        }

        int index = length - kToList;
        current = linkedList.first;
        for (int i = 1; i < index; i++) {
            current = current.next;
        }
        
        return current.data;
    }

    public static void main(String[] args) {

    }
}
