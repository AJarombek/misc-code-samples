package validatebst;

import static validatebst.ValidateBST.isBST;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import tree.TreeNode;

/**
 * Unit Testing: Cracking the Coding Interview: Question 4.5
 * @author Andrew Jarombek
 * @since 1/14/2020
 */

public class ValidateBSTTest {

    @Test
    void testValid() {
        TreeNode<Integer> root = new TreeNode<>(4,
            new TreeNode<>(2,
                new TreeNode<>(1),
                new TreeNode<>(3)
            ),
            new TreeNode<>(6,
                new TreeNode<>(5),
                new TreeNode<>(7)
            )
        );
        assertTrue(isBST(root));
    }

    @Test
    void testInvalidLeft() {
        TreeNode<Integer> root = new TreeNode<>(4,
            new TreeNode<>(1,
                new TreeNode<>(3),
                new TreeNode<>(2)
            ),
            new TreeNode<>(6,
                new TreeNode<>(5),
                new TreeNode<>(7)
            )
        );
        assertFalse(isBST(root));
    }

    @Test
    void testInvalidRight() {
        TreeNode<Integer> root = new TreeNode<>(4,
            new TreeNode<>(2,
                new TreeNode<>(1),
                new TreeNode<>(3)
            ),
            new TreeNode<>(7,
                new TreeNode<>(6),
                new TreeNode<>(5)
            )
        );
        assertFalse(isBST(root));
    }
}
