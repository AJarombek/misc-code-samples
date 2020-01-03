package myqueue;

import java.util.Stack;

/**
 * Cracking the Coding Interview: Question 3.3
 * Implement a MyQueue class which implements a queue using two stacks.
 * @author Andrew Jarombek
 * @since 1/3/2020
 */

public class MyQueue<T> {

    private Stack<T> stack;
    private Stack<T> tempStack;

    /**
     * Construct a new instance of {@code MyQueue}, which is a queue implemented with two stacks.
     */
    public MyQueue() {
        stack = new Stack<>();
        tempStack = new Stack<>();
    }

    /**
     * Add a new item on the end of the queue.  Ensure that the queue's contents are moved off the temporary stack
     * before adding the item.
     * @param item The item to be added to the queue.
     */
    public void add(T item) {
        if (stack.empty() && !tempStack.empty()) {
            while (!tempStack.empty()) {
                stack.push(tempStack.pop());
            }
        }

        stack.add(item);
    }

    /**
     * Remove an item from the front of the queue.  In order to remove an item, all the items on the main stack must
     * be moved onto the temporary stack.
     * @return The item removed from the queue.
     */
    public T remove() {
        if (tempStack.empty()) {
            while (!stack.empty()) {
                tempStack.push(stack.pop());
            }
        }

        return tempStack.pop();
    }

    /**
     * Return the item at the front of the queue without removing it.  In order to return an item from the front of the
     * queue, all the items on the main stack must be moved onto the temporary stack.
     * @return The item at the front of the queue.
     */
    public T peek() {
        if (tempStack.empty()) {
            while (!stack.empty()) {
                tempStack.push(stack.pop());
            }
        }

        return tempStack.peek();
    }

    /**
     * Determine if the stack is empty or not.
     * @return {@code true} if the stack is empty, {@code} false otherwise.
     */
    public boolean isEmpty() {
        return stack.empty() && tempStack.empty();
    }
}
