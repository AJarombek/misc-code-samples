package buildorder;

import static buildorder.BuildOrder.construct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * Unit Testing: Cracking the Coding Interview: Question 4.7
 * @author Andrew Jarombek
 * @since 1/17/2020
 */

class BuildOrderTest {

    @Test
    void test() {
        List<Character> projects = List.of('a', 'b', 'c', 'd', 'e', 'f');
        List<Map.Entry<Character, Character>> dependencies = List.of(
            new AbstractMap.SimpleEntry<>('a', 'd'),
            new AbstractMap.SimpleEntry<>('f', 'b'),
            new AbstractMap.SimpleEntry<>('b', 'd'),
            new AbstractMap.SimpleEntry<>('f', 'a'),
            new AbstractMap.SimpleEntry<>('d', 'c')
        );

        List<Character> resultList = construct(projects, dependencies);
        assertEquals(6, resultList.size());

        assertEquals('e', resultList.get(0));
        assertEquals('f', resultList.get(1));
        assertEquals('a', resultList.get(2));
        assertEquals('b', resultList.get(3));
        assertEquals('d', resultList.get(4));
        assertEquals('c', resultList.get(5));
    }
}
