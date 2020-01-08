package minimaltree;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import tree.TreeNode;

/**
 * Unit Test: Cracking the Coding Interview: Question 4.2
 * @author Andrew Jarombek
 * @since 1/8/2020
 */

public class MinimalTreeTest {

    @Test
    void test() {
        TreeNode<Integer> result = MinimalTree.createBinarySearchTree(new int[] {1,2,3,4,5});
        assertNotNull(result);

        assertEquals(3, result.value);

        assertEquals(2, result.left.value);
        assertEquals(1, result.left.left.value);
        assertNull(result.left.right);

        assertEquals(5, result.right.value);
        assertEquals(4, result.right.left.value);
        assertNull(result.right.right);
    }
}
