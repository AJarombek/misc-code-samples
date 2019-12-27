import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Cracking the Coding Interview: Question 1.8
 * Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
 * @author Andrew Jarombek
 * @since 12/15/2019
 */

public class ZeroMatrix {

    /**
     * Solve the problem using sets of rows and columns.  The time complexity is O(nm) and the space complexity is
     * O(nm), where n is the number of rows in the matrix and m is the number of columns in the matrix.
     * @param matrix A NxM matrix represented by a two dimensional array.
     */
    private static void zeroMatrix(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        // First populate sets of rows and columns that have value of 0.
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == 0) {
                    rows.add(r);
                    cols.add(c);
                }
            }
        }

        // Then loop through all the rows which need to be zeroed.
        for (int row : rows) {
            for (int c = 0; c < matrix[0].length; c++) {
                matrix[row][c] = 0;
            }
        }

        // Then loop through all the columns which need to be zeroed.
        for (int col : cols) {
            for (int r = 0; r < matrix.length; r++) {
                matrix[r][col] = 0;
            }
        }
    }

    /**
     * Helper function for asserting that two matrices or the same height and width are equal.
     * @param matrix1 The first NxN matrix.
     * @param matrix2 The second NxN matrix.
     */
    private static void assertMatrixEqual(int[][] matrix1, int[][] matrix2) {
        for (int row = 0; row < matrix1.length; row++) {
            System.out.println(Arrays.toString(matrix1[row]) + Arrays.toString(matrix2[row]));
            assert Arrays.equals(matrix1[row], matrix2[row]);
        }
    }

    public static void main(String... args) {
        /*
         * Test the 3x3 Matrix:
         * [1 2 3]
         * [4 0 6]
         * [7 8 9]
         */
        int[][] matrix = new int[][] {
            new int[] {1, 2, 3},
            new int[] {4, 0, 6},
            new int[] {7, 8, 9}
        };

        int[][] expectedResultMatrix = new int[][] {
            new int[] {1, 0, 3},
            new int[] {0, 0, 0},
            new int[] {7, 0, 9}
        };

        zeroMatrix(matrix);
        assertMatrixEqual(matrix, expectedResultMatrix);

        /*
         * Test the 3x3 Matrix:
         * [1 2 3]
         * [0 5 6]
         * [7 8 9]
         */
        matrix = new int[][] {
            new int[] {1, 2, 3},
            new int[] {0, 5, 6},
            new int[] {7, 8, 9}
        };

        expectedResultMatrix = new int[][] {
            new int[] {0, 2, 3},
            new int[] {0, 0, 0},
            new int[] {0, 8, 9}
        };

        zeroMatrix(matrix);
        assertMatrixEqual(matrix, expectedResultMatrix);
    }
}
