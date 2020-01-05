package sortstack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * Unit Testing: Cracking the Coding Interview: Question 3.5
 * @author Andrew Jarombek
 * @since 1/5/2020
 */

class SortStackTest {

    @Test
    void test() {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(2);
        stack.push(4);
        stack.push(1);
        stack.push(3);

        SortStack.sort(stack);

        assertEquals("[5, 4, 3, 2, 1]", stack.toString());
        assertEquals(1, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(5, stack.pop());
    }
}
