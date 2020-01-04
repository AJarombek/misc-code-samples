package myqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

/**
 * Unit Tests: Cracking the Coding Interview: Question 3.4
 * @author Andrew Jarombek
 * @since 1/3/2020
 */

class MyQueueTest {

    @Test
    void testEmptyQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        assertTrue(queue.isEmpty());
        assertThrows(EmptyStackException.class, queue::peek);
        assertThrows(EmptyStackException.class, queue::remove);
    }

    @Test
    void testPopulatedQueue() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertEquals(3, queue.peek());
        assertEquals(3, queue.remove());
        assertEquals(2, queue.remove());
        assertEquals(1, queue.remove());
    }

    @Test
    void testSwitchingBetweenAddRemove() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertEquals(3, queue.remove());
        queue.add(4);
        assertEquals(4, queue.peek());
        queue.add(5);
        assertEquals(5, queue.remove());
    }
}
