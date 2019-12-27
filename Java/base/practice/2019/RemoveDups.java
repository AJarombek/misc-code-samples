import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Cracking the Coding Interview: Question 2.1
 * Write code to remove duplicates from an unsorted linked list.  Follow Up: How would you solve this problem if
 * a temporary buffer is not allowed.
 * @author Andrew Jarombek
 * @since 12/17/2019
 */

// In 2020 if you want me by your side, I will be.  If you want me to leave you alone, I will.
// I want happiness for you.

public class RemoveDups {

    /**
     * Solve the problem using a Set with the unique elements and a new linked list instance.  The space complexity
     * is O(n) and the time complexity is O(n).
     * @param list Linked list of a generic type.
     * @param <E> Generic type of the linked list.
     * @return A new linked list without any duplicate values.
     */
    private static <E> LinkedList<E> removeDups(LinkedList<E> list) {
        Set<E> nodeSet = new HashSet<>();
        LinkedList<E> newList = new LinkedList<>();
        for (E node : list) {
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                newList.add(node);
            }
        }
        return newList;
    }

    /**
     * Solve the follow-up problem with two pointers.  The time complexity is O(n^2) but the space complexity is
     * improved to O(1).
     * @param list Linked list of a generic type.
     * @param <E> Generic type of the linked list.
     */
    private static <E> void removeDupsNoBuffer(LinkedList<E> list) {
        if (list.size() <= 1) {
            return;
        }

        int p1 = 0;
        int p2 = 1;

        while (p1 < list.size()) {
            E firstNode = list.get(p1);

            while (p2 < list.size()) {
                E secondNode = list.get(p2);

                if (firstNode.equals(secondNode)) {
                    list.remove(p2);
                } else {
                    p2++;
                }
            }

            p1++;
            p2 = p1 + 1;
        }
    }

    public static void main(String... args) {
        LinkedList<Integer> arg = new LinkedList<>(List.of(1, 2, 3, 4, 5, 2, 1, 6, 7, 8, 9));
        LinkedList<Integer> result = removeDups(arg);

        assert arg.size() == 11;
        assert result.size() == 9;
        System.out.println(arg.toString());
        System.out.println(result.toString());

        LinkedList<String> strList = new LinkedList<>(List.of("andy", "hi", "my", "name", "andy", "is", "andy"));
        System.out.println(strList);
        assert strList.size() == 7;

        removeDupsNoBuffer(strList);

        System.out.println(strList);
        assert strList.size() == 5;
    }
}
