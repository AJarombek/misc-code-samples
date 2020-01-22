package firstcommonanc;

import static firstcommonanc.FirstCommonAncestor.commonAncestor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import tree.TreeNode;

/**
 * Unit Testing: Cracking the Coding Interview: Question 4.8
 * @author Andrew Jarombek
 * @since 1/21/2020
 */

public class FirstCommonAncestorTest {

    @Test
    void test() {
        TreeNode<Integer> root = new TreeNode<>(4,
            new TreeNode<>(3,
                new TreeNode<>(2,
                    new TreeNode<>(1, null, null),
                    null),
                null),
            new TreeNode<>(5)
        );
        TreeNode<Integer> result = commonAncestor(root, root.left.left.left, root.right);
        assertEquals(result, root);
        assertEquals(result.value, root.value);
    }

    @Test
    void testNested() {
        TreeNode<Integer> root = new TreeNode<>(4,
            new TreeNode<>(3,
                new TreeNode<>(2,
                    new TreeNode<>(1, null, null),
                    new TreeNode<>(0, null, null)),
                null),
            new TreeNode<>(5)
        );
        TreeNode<Integer> result = commonAncestor(root, root.left.left.right, root.left.left.left);
        //assertEquals(root.left.left, result);
        assertEquals(root.left.left.value, result.value);
    }
}
