/**
 * Interview Practice Problem: https://leetcode.com/problems/flipping-an-image/
 * Description:
 *      Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
 *
 *      To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0]
 *      horizontally results in [0, 1, 1].  To invert an image means that each 0 is replaced by 1, and each 1 is
 *      replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
 *
 * @author Andrew Jarombek
 * @since 10/26/2019
 */

public class FlipAndInvertImage {

    /**
     * Solution to the flip and invert matrix problem.  Uses a nested for loop, so the runtime is O(RC), where R is the
     * number of rows in the matrix and C is the number of columns in the matrix.  Since a new matrix is created, the
     * space complexity is also O(RC).
     * @param image The original image that needs to be flipped and inverted.
     * @return The flipped and inverted matrix.
     */
    private static int[][] flipAndInvertImage(int[][] image) {
        int[][] result = new int[image.length][image[0].length];
        for (int r = 0; r < image.length; r++) {
            int rowLength = image[r].length;
            for (int c = 0; c < image[r].length; c++) {
                // Use XOR (^) instead of negation (~) due to the fact integers are implemented as two's compliment in
                // Java: https://stackoverflow.com/a/2513659
                result[r][rowLength - c - 1] = image[r][c] ^ 1;
            }
        }

        return result;
    }

    public static void main(String... args) {
        int[][] originalImage = new int[3][3];
        originalImage[0] = new int[] {1, 1, 0};
        originalImage[1] = new int[] {1, 0, 1};
        originalImage[2] = new int[] {0, 0, 0};

        int[][] newImage = flipAndInvertImage(originalImage);

        assert newImage[0][0] == 1 && newImage[0][1] == 0 && newImage[0][2] == 0;
        assert newImage[1][0] == 0 && newImage[1][1] == 1 && newImage[1][2] == 0;
        assert newImage[2][0] == 1 && newImage[2][1] == 1 && newImage[2][2] == 1;
    }
}
