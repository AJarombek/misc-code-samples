import java.util.HashMap;
import java.util.Scanner;

/**
 * Given two strings, write a method to decide if one is a permutation of the other
 * @author Andrew Jarombek
 * @since 6/4/2017
 */

public class CheckPermutation {

    public static void checkPermutation(String s1, String s2) {
        HashMap<Character, Integer> map1 = generateCharacterMap(s1);
        HashMap<Character, Integer> map2 = generateCharacterMap(s2);

        if (map1.equals(map2)) {
            System.out.println("The Strings are permutations of each other!");
        } else {
            System.out.println("The Strings are NOT permutations.");
        }
    }

    public static HashMap<Character, Integer> generateCharacterMap(String s) {
        HashMap<Character, Integer> contents = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (contents.containsKey(c)) {
                int value = contents.get(c);
                contents.replace(c, value + 1);
            } else {
                contents.put(c, 1);
            }
        }

        return contents;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string: ");
        String s1 = scanner.nextLine();

        System.out.println("Enter another string: ");
        String s2 = scanner.nextLine();

        checkPermutation(s1, s2);
    }
}
