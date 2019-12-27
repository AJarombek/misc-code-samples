import java.util.HashMap;

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 * [Gartner Interview Prep Question]
 * @author Andrew Jarombek
 * @since 1/9/2017
 */
public class UniqueCharacters {

    public static boolean isUnique(String str) {
        HashMap<Character,Integer> found = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (found.get(c) != null)
                return false;
            else
                found.put(c,1);
        }
        return true;
    }

    public static void main(String[] args) {
        String test1 = "banana";
        String test2 = "orange";
        System.out.println(isUnique(test1));
        System.out.println(isUnique(test2));
    }
}
