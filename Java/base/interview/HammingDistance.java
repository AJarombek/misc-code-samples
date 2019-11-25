/**
 * Interview Practice Problem: https://leetcode.com/problems/score-after-flipping-matrix/
 * Result: [SUCCESS - 0:30]
 * Description:
 *      The Hamming distance between two integers is the number of positions at which the corresponding bits are
 *      different.  Given two integers x and y, calculate the Hamming distance.
 *
 * @author Andrew Jarombek
 * @since 11/16/2019
 */

public class HammingDistance {

    /**
     * My initial solution to the problem using two binary string representations of the integers.  The time complexity
     * is O(n) where n is the length of the longest of the two binary strings.  The space complexity is O(n) since new
     * variables are initialized in each for loop iteration.  It turns out there are better ways to solve this problem,
     * as I'll demonstrate next.
     * @param x The first integer.
     * @param y The second integer.
     * @return The hamming distance between the two integers.
     */
    private static int hammingDistance(int x, int y) {
        String binaryX = Integer.toBinaryString(x);
        String binaryY = Integer.toBinaryString(y);

        int distance = 0;
        int maxLength = Math.max(binaryX.length(), binaryY.length());
        for (int i = 0; i < maxLength; i++) {
            char bitX = ((binaryX.length() - 1 - i) >= 0) ? binaryX.charAt(binaryX.length() - 1 - i) : '0';
            char bitY = ((binaryY.length() - 1 - i) >= 0) ? binaryY.charAt(binaryY.length() - 1 - i) : '0';

            if (bitX != bitY) {
                distance++;
            }
        }

        return distance;
    }

    /**
     * Faster approach using a bitwise XOR and a built-in function to count the bits.  Based on this post:
     * https://leetcode.com/problems/hamming-distance/discuss/94698/Java-1-Line-Solution-%3AD
     * @param x The first integer.
     * @param y The second integer.
     * @return The hamming distance between the two integers.
     */
    private static int hammingDistanceV2(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    /**
     * Similar to V2 except I implement my own bit counter instead of using the standard library's
     * {@code Integer.bitCount} static method.
     * @param x The first integer.
     * @param y The second integer.
     * @return The hamming distance between the two integers.
     */
    private static int hammingDistanceV3(int x, int y) {
        int xXORy = x ^ y;

        int distance = 0;
        while (xXORy > 0) {
            if (xXORy % 2 != 0) {
                distance++;
            }

            xXORy = xXORy >> 1;
        }

        return distance;
    }

    public static void main(String... args) {
        int result = hammingDistance(4, 1);
        assert result == 2;

        int resultV2 = hammingDistanceV2(4, 1);
        assert resultV2 == 2;

        int resultV3 = hammingDistanceV3(4, 1);
        assert resultV3 == 2;
    }
}
