import java.util.HashSet;

/**
 * Cracking the Coding Interview: Question 1.1
 * Implement an algorithm to determine if a string has all unique characters.  What if you cannot use additional
 * data structures?
 * @author Andrew Jarombek
 * @since 11/17/2019
 */

public class UniqueString {

    /**
     * Solve the unique characters problem by using a set data structure.  The time complexity is O(n), where n is the
     * length of the string.  This is because insertion and lookup times of hash sets are O(1).  The space complexity
     * is O(n) because the set is populated with characters, at most the length of the initial string.
     * @param string String to check for duplicate characters.
     * @return {@code true} if all characters in the input string are unique.
     */
    private static boolean isUnique(String string) {
        HashSet<Character> uniqueCharacters = new HashSet<>();

        for (int i = 0; i < string.length(); i++) {
            boolean added = uniqueCharacters.add(string.charAt(i));

            if (!added) {
                return false;
            }
        }

        return true;
    }

    /**
     * Solve the unique characters problem without an additional data structure.  The space complexity is the same
     * [O(n)] because we save a character in a variable in each outer for loop.  The time complexity is now worse,
     * at O(n^2).
     * @param string String to check for duplicate characters.
     * @return {@code true} if all characters in the input string are unique.
     */
    private static boolean isUniqueNoDataStructures(String string) {
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            for (int j = i + 1; j < string.length(); j++) {
                if (c == string.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String... args) {
        boolean result1 = isUnique("andy");
        assert result1;

        boolean result2 = isUnique("aandy");
        assert !result2;

        boolean result3 = isUnique("abca");
        assert !result3;

        boolean result4 = isUniqueNoDataStructures("andy");
        assert result4;

        boolean result5 = isUniqueNoDataStructures("aandy");
        assert !result5;

        boolean result6 = isUniqueNoDataStructures("abca");
        assert !result6;
    }
}
