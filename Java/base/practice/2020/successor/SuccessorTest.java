package successor;

import static successor.Successor.findSuccessor;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tree.TreeNodeParentRef;

/**
 * Unit Tests: Cracking the Coding Interview: Question 4.6
 * @author Andrew Jarombek
 * @since 1/15/2020
 */

class SuccessorTest {

    private static TreeNodeParentRef<Integer> balancedBST;

    @BeforeAll
    static void setup() {
        TreeNodeParentRef<Integer> root = new TreeNodeParentRef<>(4);

        TreeNodeParentRef<Integer> left1 = new TreeNodeParentRef<>(2, root);
        TreeNodeParentRef<Integer> right1 = new TreeNodeParentRef<>(6, root);
        root.left = left1;
        root.right = right1;

        TreeNodeParentRef<Integer> left2_1 = new TreeNodeParentRef<>(1, left1);
        TreeNodeParentRef<Integer> right2_1 = new TreeNodeParentRef<>(3, left1);
        left1.left = left2_1;
        left1.right = right2_1;

        TreeNodeParentRef<Integer> left2_2 = new TreeNodeParentRef<>(5, right1);
        TreeNodeParentRef<Integer> right2_2 = new TreeNodeParentRef<>(7, right1);
        right1.left = left2_2;
        right1.right = right2_2;

        balancedBST = root;
    }

    @Test
    void testBalancedBST() {
        TreeNodeParentRef<Integer> result = findSuccessor(balancedBST);
        assertEquals(balancedBST.right, result);

        result = findSuccessor(balancedBST.left.right);
        assertEquals(balancedBST.right, result);
    }
}
