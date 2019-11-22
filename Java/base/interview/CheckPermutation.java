import java.util.HashMap;
import java.util.Map;

/**
 * Cracking the Coding Interview: Question 1.2
 * Given two strings, write a method to decide if one is a permutation of the other.
 * @author Andrew Jarombek
 * @since 11/20/2019
 */

public class CheckPermutation {

    // I'm so proud of you, you have come so far.  You deserve the very best, always.

    /**
     * Determine if a string is a permutation of another string using a HashMap and two for loops.  The time complexity
     * is O(n), where n is the length of either string.  The space complexity is O(n), where n is the length
     * of either string.
     * @param original The original string.  This function checks if another string is a permutation of the original
     *                 string.
     * @param permutation The other string.  This string may or may not be a permutation of the original string.
     * @return {@code true} if the second string is a permutation of the original string, {@code false} otherwise.
     */
    private static boolean check(String original, String permutation) {
        if (original.length() != permutation.length()) {
            return false;
        }

        Map<Character, Integer> originalChars = new HashMap<>();

        for (int i = 0; i < original.length(); i++) {
            Integer value = originalChars.get(original.charAt(i));
            originalChars.put(original.charAt(i), (value == null) ? 1 : value + 1);
        }

        for (int i = 0; i < permutation.length(); i++) {
            Integer charCount = originalChars.get(permutation.charAt(i));

            if (charCount == null || charCount == 0) {
                return false;
            } else {
                originalChars.put(permutation.charAt(i), charCount - 1);
            }
        }

        return true;
    }

    public static void main(String... args) {
        // False - Different Lengths
        boolean result1 = check("abc", "cb");
        assert !result1;

        // True - Same lengths and characters
        boolean result2 = check("andy", "dyna");
        assert result2;

        // False - Same Lengths but different characters
        boolean result3 = check("andy", "dynb");
        assert !result3;

        // True - Same lengths and characters
        boolean result4 = check("abc", "cab");
        assert result4;
    }
}
