package listofdepths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import tree.TreeNode;

import java.util.List;

/**
 * Unit Testing: Cracking the Coding Interview: Question 4.3
 * @author Andrew Jarombek
 * @since 1/12/2020
 */

public class ListOfDepthsTest {

    @Test
    void test() {
        // Construct the binary tree.
        TreeNode<Integer> root = new TreeNode<>(5,
            new TreeNode<>(3,
                new TreeNode<>(2,
                    new TreeNode<>(1, null, null),
                    null
                ),
                new TreeNode<>(4, null, null)
            ),
            new TreeNode<>(7,
                new TreeNode<>(6, null,null),
                new TreeNode<>(8, null, null)
            )
        );

        List<List<TreeNode<Integer>>> resultList = ListOfDepths.dfs(root);

        assertEquals(4, resultList.size());
        assertEquals(1, resultList.get(0).size());
        assertEquals(2, resultList.get(1).size());
        assertEquals(4, resultList.get(2).size());
        assertEquals(1, resultList.get(3).size());

        assertEquals(5, resultList.get(0).get(0).value);
        assertThrows(IndexOutOfBoundsException.class, () -> resultList.get(0).get(1));

        assertEquals(3, resultList.get(1).get(0).value);
        assertEquals(7, resultList.get(1).get(1).value);
        assertThrows(IndexOutOfBoundsException.class, () -> resultList.get(1).get(2));

        assertEquals(2, resultList.get(2).get(0).value);
        assertEquals(4, resultList.get(2).get(1).value);
        assertEquals(6, resultList.get(2).get(2).value);
        assertEquals(8, resultList.get(2).get(3).value);
        assertThrows(IndexOutOfBoundsException.class, () -> resultList.get(2).get(4));

        assertEquals(1, resultList.get(3).get(0).value);
        assertThrows(IndexOutOfBoundsException.class, () -> resultList.get(3).get(1));
    }
}
