/**
 * Cracking the Coding Interview: Question 1.3
 * Write a method to replace all spaces in a string with '%20'.  You may assume that the string has sufficient space at
 * the end to hold the additional characters, and that you are given the 'true' length of the string.  (Note: if
 * implementing in Java, please use a character array so that you can perform this operation in place.)
 * @author Andrew Jarombek
 * @since 11/21/2019
 */

public class URLify {

    /**
     * Solution for creating a space-escaped URL string.  Use a while loop and a character array.  The time and space
     * complexity are both O(n), where n is the length of the string to escape.
     * @param string Original unescaped string.
     * @param length The length of the original string minus the whitespace at the end.
     * @return An HTTP URL string with whitespace escaped.
     */
    private static String replace(String string, int length) {
        char[] chars = string.toCharArray();

        int newIndex = chars.length - 1;
        int originalIndex = length - 1;

        while (originalIndex >= 0) {
            if (chars[originalIndex] == ' ') {
                chars[newIndex-2] = '%';
                chars[newIndex-1] = '2';
                chars[newIndex] = '0';

                newIndex -= 3;
            } else {
                chars[newIndex] = chars[originalIndex];
                newIndex--;
            }

            originalIndex--;
        }

        return new String(chars);
    }

    public static void main(String... args) {
        String urlString1 = replace("oh hey  ", 6);
        assert urlString1.equals("oh%20hey");

        String urlString2 = replace(" jarombek com url      ", 17);
        assert urlString2.equals("%20jarombek%20com%20url");

        String urlString3 = replace("two  spaces    ", 11);
        assert urlString3.equals("two%20%20spaces");
    }
}
