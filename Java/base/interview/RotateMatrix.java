/**
 * Cracking the Coding Interview: Question 1.7
 * Given an image represented by a NxN matrix, where each pixel in the image is 4 bytes, write a method to rotate the
 * image by 90 degrees.  Can you do this in place?
 * @author Andrew Jarombek
 * @since 11/29/2019
 */

public class RotateMatrix {

    private static int[][] rotate(int[][] matrix) {
        // TODO
    }

    public static void main(String... args) {
        /*
         * Test the 1x1 Matrix:
         * [1]
         */
        int[][] matrix = new int[][] {new int[] {1}};

        int[][] rotatedMatrix = rotate();

        /*
         * Test the 2x2 Matrix:
         * [1 2]
         * [3 4]
         */
        int[][] matrix2 = new int[][] {
            new int[] {1, 2},
            new int[] {3, 4}
        };

        int[][] rotatedMatrix2 = rotate();
    }
}
