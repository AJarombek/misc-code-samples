package checkbalanced;

import static checkbalanced.CheckBalanced.isBalanced;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import tree.TreeNode;

/**
 * Unit Testing: Cracking the Coding Interview: Question 4.4
 * @author Andrew Jarombek
 * @since 1/13/2020
 */

class CheckBalancedTest {

    @Test
    void testBalanced() {
        TreeNode<Integer> root = new TreeNode<>(2, new TreeNode<>(1), new TreeNode<>(3));
        assertTrue(isBalanced(root));
    }

    @Test
    void testImbalanced() {
        TreeNode<Integer> root = new TreeNode<>(4,
            new TreeNode<>(3,
                new TreeNode<>(2,
                    new TreeNode<>(1, null, null),
                null),
            null),
            new TreeNode<>(5)
        );
        assertFalse(isBalanced(root));
    }
}
