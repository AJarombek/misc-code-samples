package src;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit Testing: Cracking the Coding Interview: Question 3.1
 * @author Andrew Jarombek
 * @since 12/27/2019
 */

class ThreeInOneTest {

    @Test
    void testEmpty() {
        ThreeInOne<Integer> threeInOne = new ThreeInOne<>(Integer.class);
        assertTrue(threeInOne.isEmpty(1));
        assertTrue(threeInOne.isEmpty(2));
        assertTrue(threeInOne.isEmpty(3));

        assertNull(threeInOne.peek(1));
        assertNull(threeInOne.peek(2));
        assertNull(threeInOne.peek(3));

        assertNull(threeInOne.pop(1));
        assertNull(threeInOne.pop(2));
        assertNull(threeInOne.pop(3));
    }

    @Test
    void testPushPeekPop() {
        ThreeInOne<Character> threeInOne = new ThreeInOne<>(Character.class);
        threeInOne.push(2, 'a');

        assertNull(threeInOne.peek(1));
        assertEquals('a', (char) threeInOne.peek(2));
        assertNull(threeInOne.peek(3));

        assertTrue(threeInOne.isEmpty(1));
        assertFalse(threeInOne.isEmpty(2));
        assertTrue(threeInOne.isEmpty(3));

        assertEquals('a', (char) threeInOne.pop(2));
        assertTrue(threeInOne.isEmpty(2));
    }

    @Test
    void testPushX4() {
        ThreeInOne<String> threeInOne = new ThreeInOne<>(String.class);
        threeInOne.push(1, "a");
        threeInOne.push(1, "b");
        threeInOne.push(1, "c");
        threeInOne.push(1, "d");

        assertFalse(threeInOne.isEmpty(1));

        assertEquals("d", threeInOne.pop(1));
        assertEquals("c", threeInOne.pop(1));
        assertEquals("b", threeInOne.pop(1));
        assertEquals("a", threeInOne.pop(1));
        assertNull(threeInOne.pop(1));
    }
}
