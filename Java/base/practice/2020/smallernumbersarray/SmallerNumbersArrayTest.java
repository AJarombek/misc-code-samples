package smallernumbersarray;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit Testing: Smaller numbers array problem.
 * @author Andrew Jarombek
 * @since 3/1/2020
 */

class SmallerNumbersArrayTest {

    @Test
    void test() {
        int[] input = new int[] {6, 5, 4, 8};
        int[] result = SmallerNumbersArray.smallerNumbersThanCurrent(input);
        assertArrayEquals(result, new int[] {2, 1, 0, 3});
    }
}
