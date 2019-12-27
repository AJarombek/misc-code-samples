import linkedlist.BasicSinglyLinkedList;
import linkedlist.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Cracking the Coding Interview: Question 2.6
 * Implement a function to check if a linked list is a palindrome.
 * @author Andrew Jarombek
 * @since 12/24/2019
 */

public class LinkedListPalindrome {

    /**
     * Solve the problem with a built-in linked list.  The time complexity is O(n^2) [get() is O(n) for linked lists]
     * and the space complexity is O(1).
     * @param linkedList A linked list that may be a palindrome.
     * @param <T> The generic type of the linked list contents.
     * @return {@code true} if the linked list is a palindrome, {@code false} otherwise.
     */
    private static <T> boolean isPalindrome(LinkedList<T> linkedList) {
        for (int i = 0; i <= linkedList.size() / 2; i++) {
            if (!linkedList.get(i).equals(linkedList.get(linkedList.size() - 1 - i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Solve the problem with a custom linked list.  The time complexity is O(n) [get() is O(1) for arrays]
     * and the space complexity is O(n).
     * @param linkedList A linked list that may be a palindrome.
     * @param <T> The generic type of the linked list contents.
     * @return {@code true} if the linked list is a palindrome, {@code false} otherwise.
     */
    private static <T> boolean isPalindrome(BasicSinglyLinkedList<T> linkedList) {
        ArrayList<T> arrayList = new ArrayList<>();
        Node<T> current = linkedList.first;
        while (current != null) {
            arrayList.add(current.data);
            current = current.next;
        }

        for (int i = 0; i <= arrayList.size() / 2; i++) {
            if (!arrayList.get(i).equals(arrayList.get(arrayList.size() - 1 - i))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test with a built-in LinkedList
        LinkedList<Integer> intLinkedList = new LinkedList<>(List.of(1, 2, 3, 4, 5, 6, 7));
        assert !isPalindrome(intLinkedList);

        LinkedList<Character> charLinkedList = new LinkedList<>(List.of('a', 'a'));
        assert isPalindrome(charLinkedList);

        charLinkedList = new LinkedList<>(List.of('a', 'b', 'a'));
        assert isPalindrome(charLinkedList);

        charLinkedList = new LinkedList<>(List.of('a', 'b', 'c', 'a'));
        assert !isPalindrome(charLinkedList);

        // Test with a custom Linked List
        BasicSinglyLinkedList<Character> charCustomLinkedList = new BasicSinglyLinkedList<>();
        charCustomLinkedList.first = new Node<>('a', new Node<>('a'));
        assert isPalindrome(charCustomLinkedList);

        charCustomLinkedList.first = new Node<>('a', new Node<>('b', new Node<>('a')));
        assert isPalindrome(charCustomLinkedList);

        charCustomLinkedList.first = new Node<>('a', new Node<>('b', new Node<>('c', new Node<>('a'))));
        assert !isPalindrome(charCustomLinkedList);
    }
}
