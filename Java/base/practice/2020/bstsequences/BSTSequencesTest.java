package bstsequences;

import static bstsequences.BSTSequences.possibleSequences;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import tree.TreeNode;

import java.util.List;

/**
 * Unit Testing: Cracking the Coding Interview: Question 4.9
 * @author Andrew Jarombek
 * @since 1/25/2020
 */

class BSTSequencesTest {

    @Test
    void test() {
        TreeNode<Integer> input = new TreeNode<>(2, new TreeNode<>(1), new TreeNode<>(3));
        List<List<Integer>> result = possibleSequences(input);
        assertEquals(2, result.size());
    }
}
