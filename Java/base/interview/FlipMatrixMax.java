import java.util.ArrayList;
import java.util.List;

/**
 * Interview Practice Problem: https://leetcode.com/problems/score-after-flipping-matrix/
 * Result: [FAILURE - 1:30 +]
 * Description:
 *      Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
 *
 *      To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0]
 *      horizontally results in [0, 1, 1].  To invert an image means that each 0 is replaced by 1, and each 1 is
 *      replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
 *
 * @author Andrew Jarombek
 * @since 11/15/2019
 */

public class FlipMatrixMax {

    private static int matrixScoreNMoves(int[][] matrix, int maxMoves) {
        // TODO
        int maxScore = 0;
        int y = matrix.length;
        int x = matrix[0].length;

        List<int[][]> matrixLists = new ArrayList<>();
        matrixLists.add(matrix);

        while (maxMoves > 0) {
            List<int[][]> newMatrixLists = new ArrayList<>();

            for (int[][] matrixArray : matrixLists) {

            }

            maxMoves--;
        }

        return maxScore;
    }

    public static void main(String... args) {
        int result1 = matrixScoreNMoves(new int[][] {
                new int[] {0, 0, 1, 1},
                new int[] {1, 0, 1, 0},
                new int[] {1, 1, 0, 0}
        }, 2);
        //assert result1 == 36;

        int result2 = matrixScoreNMoves(new int[][] {
                new int[] {0, 1},
                new int[] {1, 0},
                new int[] {1, 1}
        }, 1);
        //assert result2 == 7;

        // Code used to help me towards the solution. First, review bitwise operators and bytes in Java.
        byte four = 0b100;
        assert Byte.toUnsignedInt(four) == 4;

        byte ten = 0b1010;
        assert Byte.toUnsignedInt(ten) == 10;

        assert (ten & 0b0101) == 0b0; // Bitwise AND
        assert ~ten == -11; // Bitwise Complement.  Flips the bits and gives the 2's complement of the number.
        assert (ten ^ 1) == 0b1011; // Bitwise XOR

        // Flip the bits of 10
        assert (ten ^ 0b1111) == 0b0101;

        // 8 OR 7 == 15
        assert (0b1000 | 0b111) == 0b1111;

        // Shift operators
        assert (0b10 >> 1) == 1;  // Signed right shift operator
        assert (0b100 >>> 1) == 2; // Unsigned right shift operator

        assert (0b10 << 1) == 4; // Signed left shift operator
        // assert (0b100 <<< 1) == 16; Java does not have an unsigned left shift operator - it is logically equivalent
        // to a signed left shift operator and isn't needed.
    }
}
