import java.util.HashMap;
import java.util.Map;

/**
 * Cracking the Coding Interview: Question 1.4
 * Given a string, write a function to check if it is a permutation of a palindrome.  A palindrome is a word or phrase
 * that is the same forwards and backwards.  A permutation is a rearrangement of letters.  The palindrome does not
 * need to be limited to just dictionary words
 * @author Andrew Jarombek
 * @since 11/23/2019
 */

public class PalindromePermutation {

    /**
     * Solve the palindrome permutation problem by checking the number of odd occurrences of characters in a string.
     * The time complexity is O(n) and the space complexity is O(n).
     * @param str A string that may be a permutation of a palindrome.
     * @return {@code true} if the string is a palindrome, {@code false} otherwise.
     */
    private static boolean isPermutation(String str) {
        str = str.toLowerCase();
        Map<Character, Integer> characters = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                if (characters.containsKey(c)) {
                    characters.put(c, characters.get(c) + 1);
                } else {
                    characters.put(c, 1);
                }
            }
        }

        boolean foundCharWithOneOccurance = false;
        for (int count : characters.values()) {
            if (count % 2 == 1) {
                if (foundCharWithOneOccurance) {
                    return false;
                } else {
                    foundCharWithOneOccurance = true;
                }
            }
        }

        return true;
    }

    public static void main(String... args) {
        boolean result1 = isPermutation("Tact Coa");
        assert result1;

        boolean result2 = isPermutation("Palindrome");
        assert !result2;

        boolean result3 = isPermutation("Aab b Aab");
        assert result3;
    }
}
