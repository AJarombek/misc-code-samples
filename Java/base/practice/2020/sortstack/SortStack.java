package sortstack;

import java.util.Stack;

/**
 * Cracking the Coding Interview: Question 3.5
 * Write a program to sort a stack such that the smallest items are on the top.  You can use an additional temporary
 * stack, but you may not copy the elements into any other data structure (such as an array).  The stack supports the
 * following operations: push, pop, peek, and isEmpty.
 * @author Andrew Jarombek
 * @since 1/5/2020
 */

class SortStack {

    /**
     * Sort a stack using only a temporary stack.  The time complexity is O(n^2) and the space complexity is O(n^2).
     * @param stack The original stack to be sorted in place.
     * @param <T> The generic type of the stack.  It must be comparable.
     */
    static <T extends Comparable<? super T>> void sort(Stack<T> stack) {
        Stack<T> tempStack = new Stack<>();

        for (int i = 1; i < stack.size(); i++) {
            for (int j = 0; j < i; j++) {
                tempStack.push(stack.pop());
            }

            T item = stack.pop();
            for (int j = 0; j <= i; j++) {
                if (tempStack.empty()) {
                    stack.push(item);
                } else if (item == null) {
                    stack.push(tempStack.pop());
                } else {
                    if (item.compareTo(tempStack.peek()) < 0) {
                        stack.push(tempStack.pop());
                    } else {
                        stack.push(item);
                        item = null;
                    }
                }
            }
        }
    }
}
