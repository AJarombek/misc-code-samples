package setofstacks;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

/**
 * Unit Tests: Cracking the Coding Interview: Question 3.3
 * @author Andrew Jarombek
 * @since 1/2/2020
 */

class SetOfStacksTest {

    @Test
    void testEmptyStack() {
        SetOfStacks<Integer> stacks = new SetOfStacks<>();
        assertThrows(EmptyStackException.class, stacks::pop);
        assertThrows(EmptyStackException.class, () -> stacks.popAt(0));
    }

    @Test
    void testPushOnce() {
        SetOfStacks<Integer> stacks = new SetOfStacks<>();
        stacks.push(1);
        assertEquals(1, stacks.pop());
    }

    @Test
    void testMultipleStacks() {
        SetOfStacks<Integer> stacks = new SetOfStacks<>();
        stacks.push(1);
        stacks.push(2);
        stacks.push(3);
        stacks.push(4);
        stacks.push(5);
        stacks.push(6);
        stacks.push(7);

        assertEquals(5, stacks.popAt(0));
        assertEquals(4, stacks.popAt(0));
        assertEquals(7, stacks.popAt(1));

        assertEquals(6, stacks.pop());
        assertEquals(3, stacks.pop());
        assertEquals(2, stacks.pop());
        assertEquals(1, stacks.pop());
    }
}
