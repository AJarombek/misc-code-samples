/**
 * Interview Practice Problem: https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/
 * Result: [SUCCESS: 0:26]
 * Description:
 *      Given n and m which are the dimensions of a matrix initialized by zeros and given an array indices where
 *      indices[i] = [ri, ci]. For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.
 *
 *      Return the number of cells with odd values in the matrix after applying the increment to all indices.
 *
 * @author Andrew Jarombek
 * @since 11/13/2019
 */

public class OddValueCells {

    /**
     * Solve the problem using nested for loops and a counter of odd values.  The time complexity is O(nm), where n is
     * the number of elements in the indices array and m is the max(number of rows in matrix, number of columns in
     * matrix).  The space complexity is O(nm), where n is the number of rows in the matrix and m is the number of
     * columns in the matrix.
     * @param n The number of rows in the matrix.
     * @param m The number of columns in the matrix.
     * @param indices Rows and columns whose values should be incremented in the matrix.
     * @return The number of odd numbers in the final matrix.
     */
    private static int oddCells(int n, int m, int[][] indices) {
        int[][] matrix = new int[n][m];
        int odds = 0;

        for (int i = 0; i < indices.length; i++) {
            for (int c = 0; c < m; c++) {
                matrix[indices[i][0]][c] = matrix[indices[i][0]][c] += 1;
                if (matrix[indices[i][0]][c] % 2 == 0) {
                    odds--;
                } else {
                    odds++;
                }
            }

            for (int r = 0; r < n; r++) {
                matrix[r][indices[i][1]] = matrix[r][indices[i][1]] += 1;
                if (matrix[r][indices[i][1]] % 2 == 0) {
                    odds--;
                } else {
                    odds++;
                }
            }
        }

        return odds;
    }

    public static void main(String... args) {
        int result = oddCells(2, 3, new int[][] {new int[] {0, 1}, new int[] {1, 1}});
        assert result == 6;
    }
}
