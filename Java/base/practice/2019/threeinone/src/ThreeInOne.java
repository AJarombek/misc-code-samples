package src;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Cracking the Coding Interview: Question 3.1
 * Describe how you could use a single array to implement three stacks.
 * @author Andrew Jarombek
 * @since 12/27/2019
 */

class ThreeInOne<T> {
    private T[] array;
    private int[] sizes;

    /**
     * Construct a new three-in-one stack data structure instance.
     */
    @SuppressWarnings("unchecked")
    ThreeInOne(Class<T> clazz) {
        array = (T[]) Array.newInstance(clazz, 10);
        sizes = new int[3];
    }

    /**
     * Pop an item off one of the three stacks.
     * @param stackId The identifier of the stack to pop an item off of. stackId IN {1, 2, 3}
     * @return The item popped off the stack.
     */
    public T pop(int stackId) {
        int top = sizes[stackId - 1];
        if (top == 0) {
            return null;
        } else {
            int index = top * 3 - (3 - stackId);
            T result = array[index];
            array[index] = null;
            sizes[stackId - 1] = sizes[stackId - 1] - 1;
            return result;
        }
    }

    /**
     * Push a new item onto one of the three stacks.
     * @param stackId The identifier of the stack to push the new item onto. stackId IN {1, 2, 3}
     * @param item Item to push onto the stack.
     */
    public void push(int stackId, T item) {
        int top = sizes[stackId - 1];
        int newTop = top + 1;
        sizes[stackId - 1] = newTop;

        int index = newTop * 3 - (3 - stackId);
        if (index >= array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[index] = item;
    }

    /**
     * Peek at the item at the top of one of the three stacks.
     * @param stackId The identifier of the stack to peek into. stackId IN {1, 2, 3}
     * @return The item at the top of the stack.
     */
    public T peek(int stackId) {
        int top = sizes[stackId - 1];
        if (top == 0) {
            return null;
        } else {
            return array[top * 3 - (3 - stackId)];
        }
    }

    /**
     * Determine if one of the three stacks is empty or not.
     * @param stackId The identifier of the stack to check. stackId IN {1, 2, 3}
     * @return {@code true} if the stack is empty, {@code false} otherwise.
     */
    public boolean isEmpty(int stackId) {
        return sizes[stackId - 1] == 0;
    }
}
