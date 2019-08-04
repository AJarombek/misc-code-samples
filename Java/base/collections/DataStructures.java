package collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Revisiting data structures in Java and how they compare to other languages.
 * @author Andrew Jarombek
 * @since 7/31/2019
 */

class DataStructures {
    static void execute() {
        // The diamond operator isn't supported in C#.
        // C# - []
        var yarnTempList = List.of("bamboo", "cashmere");
        var yarns = new ArrayList<>(yarnTempList);
        assert yarns.size() == 2;

        // Prove that an array list created with the diamond operator can be assigned to
        // the ArrayList<String> compile time type.
        ArrayList<String> strings = yarns;
        assert strings.size() == 2;
    }
}
