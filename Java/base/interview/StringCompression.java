/**
 * Cracking the Coding Interview: Question 1.6
 * Implement a method to perform basic string compression using the counts of repeated characters.  For example, the
 * string 'aabcccccaaa' would become a2b1c5a3.  If the "compressed" string would not become smaller than the original
 * string, your method should return the original string.  You can assume that the string is only uppercase and
 * lowercase letters (a-z).
 * @author Andrew Jarombek
 * @since 11/28/2019
 */

public class StringCompression {

    /**
     * Solve the compression problem using a for loop and string builder. It has a time complexity of O(n) and space
     * complexity of O(1).
     * @param originalString The original, non-compressed string.
     * @return A compressed string if the compression algorithm results in a shorter string,
     * the original string otherwise.
     */
    private static String compress(String originalString) {
        StringBuilder compressedStringBuilder = new StringBuilder();

        char prevChar = originalString.charAt(0);
        int prevMatchingChars = 1;
        for (int i = 1; i <= originalString.length(); i++) {
            char c = i == originalString.length() ? '-' : originalString.charAt(i);
            if (c == prevChar) {
                prevMatchingChars++;
            } else {
                compressedStringBuilder.append(prevChar);

                if (prevMatchingChars > 1) {
                    compressedStringBuilder.append(prevMatchingChars);
                }

                prevChar = c;
                prevMatchingChars = 1;
            }
        }

        String compressedString = compressedStringBuilder.toString();

        if (compressedString.length() < originalString.length()) {
            return compressedString;
        } else {
            return originalString;
        }
    }

    public static void main(String... args) {
        String result = compress("aabcccccaaa");
        assert result.equals("a2bc5a3");

        String result2 = compress("aabc");
        assert result2.equals("aabc");
    }
}
