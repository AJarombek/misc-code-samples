/**
 * Cracking the Coding Interview: Question 1.9
 * Assume you have a method isSubstring which checks if one word is a substring of another.  Given two strings, s1 and
 * s2, write code to check if s2 is a rotation of s1 using only one call to isSubstring (eg. "waterbottle" is a
 * rotation of "erbottlewat").
 * @author Andrew Jarombek
 * @since 12/16/2019
 */

public class StringRotation {

    /**
     * Solve the problem (without the single call to isSubstring() constraint) using a for loop and a substring
     * equality check.  The time complexity is O(n) and the space complexity is O(n), where n is the length of the
     * input strings.
     * @param s1 The original string.
     * @param s2 The potentially rotated string.
     * @return {@code true} if s2 is a rotation of s1, {@code} false otherwise.
     */
    private static boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        char firstChar = s1.charAt(0);
        for (int i = 0; i < s1.length(); i++) {
            if (s2.charAt(i) == firstChar && (s2.substring(i) + s2.substring(0, i)).equals(s1)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Solve the problem with the help of the three hints.  The time complexity is O(n), because contains()
     * is an O(n) operation.  The space complexity is O(1).
     * @param s1 The original string.
     * @param s2 The potentially rotated string.
     * @return {@code true} if s2 is a rotation of s1, {@code} false otherwise.
     */
    private static boolean isRotation2(String s1, String s2) {
        return s1.length() == s2.length() && (s2 + s2).contains(s1);
    }

    public static void main(String... args) {
        assert isRotation("waterbottle", "erbottlewat");
        assert !isRotation("140E45thStNewYorkNY10017", "Cigna");
        assert !isRotation("jan13", "2020");
        assert isRotation("hiimandy", "andyhiim");
        assert !isRotation("hiimandy", "andyimhi");

        assert isRotation2("waterbottle", "erbottlewat");
        assert isRotation2("hiimandy", "andyhiim");
        assert !isRotation2("hiimandy", "andyimhi");
    }
}
