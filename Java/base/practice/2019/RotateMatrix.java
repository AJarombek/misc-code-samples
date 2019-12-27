import java.util.Arrays;

/**
 * Cracking the Coding Interview: Question 1.7
 * Given an image represented by a NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the
 * image by 90 degrees.  Can you do this in place?
 * @author Andrew Jarombek
 * @since 11/29/2019
 */

public class RotateMatrix {

    /**
     * Rotate a matrix 90 degrees, creating a new matrix in the process.  The original matrix does not mutate.
     * @param matrix A NxN matrix represented by a two dimensional array.
     * @return A new matrix with the original matrix values rotated 90 degrees.
     */
    private static int[][] rotate(int[][] matrix) {
        int[][] newMatrix = new int[matrix.length][matrix.length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                newMatrix[col][matrix.length - 1 - row] = matrix[row][col];
            }
        }

        return newMatrix;
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
         * Test the 1x1 Matrix:
         * [1]
         */
        int[][] matrix = new int[][] {new int[] {1}};

        int[][] rotatedMatrix = rotate(matrix);
        assertMatrixEqual(rotatedMatrix, matrix);

        /*
         * Test the 2x2 Matrix:
         * [1 2]
         * [3 4]
         */
        int[][] matrix2 = new int[][] {
            new int[] {1, 2},
            new int[] {3, 4}
        };

        int[][] matrix2Result = new int[][] {
            new int[] {3, 1},
            new int[] {4, 2}
        };

        int[][] rotatedMatrix2 = rotate(matrix2);
        assertMatrixEqual(rotatedMatrix2, matrix2Result);

        /*
         * Test the 3x3 Matrix:
         * [1 2 3]
         * [4 5 6]
         * [7 8 9]
         */
        int[][] matrix3 = new int[][] {
            new int[] {1, 2, 3},
            new int[] {4, 5, 6},
            new int[] {7, 8, 9}
        };

        int[][] matrix3Result = new int[][] {
            new int[] {7, 4, 1},
            new int[] {8, 5, 2},
            new int[] {9, 6, 3}
        };

        int[][] rotatedMatrix3 = rotate(matrix3);
        assertMatrixEqual(rotatedMatrix3, matrix3Result);

        /*
         * Test the 4x4 Matrix:
         * [1  2  3  4 ]
         * [5  6  7  8 ]
         * [9  10 11 12]
         * [13 14 15 16]
         */
        int[][] matrix4 = new int[][] {
            new int[] {1, 2, 3, 4},
            new int[] {5, 6, 7, 8},
            new int[] {9, 10, 11, 12},
            new int[] {13, 14, 15, 16}
        };

        int[][] matrix4Result = new int[][] {
            new int[] {13, 9, 5, 1},
            new int[] {14, 10, 6, 2},
            new int[] {15, 11, 7, 3},
            new int[] {16, 12, 8, 4}
        };

        int[][] rotatedMatrix4 = rotate(matrix4);
        assertMatrixEqual(rotatedMatrix4, matrix4Result);

        /*
         * Test the 5x5 Matrix:
         * [1  2  3  4  5 ]
         * [6  7  8  9  10]
         * [11 12 13 14 15]
         * [16 17 18 19 20]
         * [21 22 23 24 25]
         */
        int[][] matrix5 = new int[][] {
            new int[] {1, 2, 3, 4, 5},
            new int[] {6, 7, 8, 9, 10},
            new int[] {11, 12, 13, 14, 15},
            new int[] {16, 17, 18, 19, 20},
            new int[] {21, 22, 23, 24, 25}
        };

        int[][] matrix5Result = new int[][] {
            new int[] {21, 16, 11, 6, 1},
            new int[] {22, 17, 12, 7, 2},
            new int[] {23, 18, 13, 8, 3},
            new int[] {24, 19, 14, 9, 4},
            new int[] {25, 20, 15, 10, 5}
        };

        int[][] rotatedMatrix5 = rotate(matrix5);
        assertMatrixEqual(rotatedMatrix5, matrix5Result);
    }
}
