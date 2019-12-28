import java.util.Stack;

/**
 * Cracking the Coding Interview: Question 3.2
 * How would you design a stack which, in addition to push and pop, has a function min which returns the minimum
 * element?  Push, pop, and min should all operate in O(1) time.
 * @author Andrew Jarombek
 * @since 12/28/2019
 */

public class MyStack<T extends Comparable<? super T>> {
    private Stack<T> stack;
    private T min;

    /**
     * Construct a custom stack data structure which utilizes object composition.
     */
    public MyStack() {
        stack = new Stack<>();
    }

    /**
     * Push a new item onto the stack.  Before pushing the new element, check if it will be the
     * smallest element on the stack.
     * @param item The item to add onto the stack.
     * @return the item that was just added.
     */
    public T push(T item) {
        if (item.compareTo(min) < 0) {
            min = item;
        }
        return stack.push(item);
    }

    /**
     * Pop an element of the top of the stack.  If the item popped is the smallest element, update the smallest element.
     * @return The item popped of the stack.
     */
    public T pop() {
        T result = stack.pop();
        if (result.equals(min)) {
            min = stack.peek();
            for (T item : stack) {
                if (item.compareTo(min) < 0) {
                    min = item;
                }
            }
        }
        return result;
    }

    /**
     * Get the smallest element (by value) in the stack.
     * @return The smallest element.
     */
    public T min() {
        return min;
    }
}
