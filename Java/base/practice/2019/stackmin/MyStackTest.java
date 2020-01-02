import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

/**
 * Unit Testing: Cracking the Coding Interview: Question 3.2
 * @author Andrew Jarombek
 * @since 12/28/2019
 */

class MyStackTest {

    @Test
    void test() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(2);
        stack.push(1);
        stack.push(3);
        stack.push(0);
        assertEquals(0, stack.min());

        stack.pop();
        assertEquals(1, stack.min());

        stack.pop();
        stack.pop();
        assertEquals(2, stack.min());

        stack.pop();
        assertNull(stack.min());
    }
}
