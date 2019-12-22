import linkedlist.BasicSinglyLinkedList;
import linkedlist.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Cracking the Coding Interview: Question 2.4
 * You have two numbers represented by a linked list, where each node contains a single digit.  The digits are stored
 * in reverse order, such that the 1's digit is at the head of the list.  Write a function that adds the two numbers
 * and returns the sum as a linked list.
 *
 * Example:
 * Input: (7 -> 1 -> 6) + (5 -> 9 -> 2).  That is, 617 + 295
 * Output: 2 -> 1 -> 9.  That is, 912.
 *
 * @author Andrew Jarombek
 * @since 12/22/2019
 */

public class SumLists {

    /**
     * Solve the problem using the built-in Collections framework LinkedList<T> class.  The time and space complexity
     * is O(n).
     * @param right A linked list representing the right-hand value of the multiplication operation.
     * @param left A linked list representing the left-hand value of the multiplication operation.
     * @return A linked list representing the sum of the multiplication operation.
     */
    private static LinkedList<Integer> sumBuiltInList(LinkedList<Integer> right, LinkedList<Integer> left) {
        int rightInt = 0;
        int leftInt = 0;

        while (right.size() > 0) {
            rightInt = (rightInt * 10) + right.removeLast();
        }

        while (left.size() > 0) {
            leftInt = (leftInt * 10) + left.removeLast();
        }

        int sum = rightInt + leftInt;

        LinkedList<Integer> result = new LinkedList<>();

        while (sum > 0) {
            result.add(sum % 10);
            sum = sum / 10;
        }

        return result;
    }

    private static <T extends Integer> BasicSinglyLinkedList<T> sumCustomList(BasicSinglyLinkedList<T> right,
                                                                              BasicSinglyLinkedList<T> left) {
        return null;  // TODO
    }

    public static void main(String[] args) {
        // Test the built-in Collections API LinkedList<T>.
        LinkedList<Integer> builtInRight = new LinkedList<>(List.of(7, 1, 6));
        LinkedList<Integer> builtInLeft = new LinkedList<>(List.of(5, 9, 2));

        LinkedList<Integer> builtInResult = sumBuiltInList(builtInRight, builtInLeft);
        assert builtInResult.toString().equals("[2, 1, 9]");

        // Test the custom Basic Linked List.
        BasicSinglyLinkedList<Integer> customRight = new BasicSinglyLinkedList<>();
        customRight.first = new Node<>(7, new Node<>(1, new Node<>(6)));
        BasicSinglyLinkedList<Integer> customLeft = new BasicSinglyLinkedList<>();
        customLeft.first = new Node<>(5, new Node<>(9, new Node<>(2)));

        BasicSinglyLinkedList<Integer> customResult = sumCustomList(customRight, customLeft);
        assert customResult.first.data == 5 &&
                customResult.first.next.data == 9 &&
                customResult.first.next.next.data == 2 &&
                customResult.first.next.next.next.data == null;
    }
}
