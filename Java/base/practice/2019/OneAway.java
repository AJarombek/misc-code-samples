/**
 * Cracking the Coding Interview: Question 1.5
 * There are three types of edits that can be performed on strings: insert a character, remove a character, or
 * replace a character.  Given two strings, write a function to check if they are one edit (or zero edits) away.
 * @author Andrew Jarombek
 * @since 11/27/2019
 */

public class OneAway {

    /**
     * Solve the problem by looping through the largest string and trying to alter it to match the smaller string.  The
     * time complexity is O(n) and space complexity is O(1).
     * @param s1 First string to compare.
     * @param s2 Second string to compare.
     * @return {@code true} if only one edit needs to be made to make the strings identical, {@code false} otherwise.
     */
    private static boolean oneAway(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }

        String largerString = s1.length() > s2.length() ? s1 : s2;
        String smallerString = s1.length() > s2.length() ? s2 : s1;

        for (int i = 0; i < largerString.length(); i++) {
            if (i == smallerString.length() || largerString.charAt(i) != smallerString.charAt(i)) {
                String largerStringRemovedChar = largerString.substring(0, i);

                String largerStringReplaceChar = i < smallerString.length() ?
                        largerString.substring(0, i) + smallerString.charAt(i): null;

                if (i + 1 < largerString.length()) {
                    largerStringRemovedChar += largerString.substring(i + 1);

                    if (largerStringReplaceChar != null) {
                        largerStringReplaceChar += largerString.substring(i + 1);
                    }
                }

                return smallerString.equals(largerStringRemovedChar) ||
                        smallerString.equals(largerStringReplaceChar);
            }
        }

        return true;
    }

    public static void main(String... args) {
        // Stay strong, as you always do.

        boolean result1 = oneAway("pale", "ple");
        assert result1;

        boolean result2 = oneAway("pales", "pale");
        assert result2;

        boolean result3 = oneAway("pale", "bale");
        assert result3;

        boolean result4 = oneAway("pale", "bake");
        assert !result4;
    }
}
