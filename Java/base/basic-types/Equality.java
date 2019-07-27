/**
 * Determining equality in Java.
 * @author Andrew Jarombek
 * @since 7/27/2019
 */

class Equality {
    static void Execute() {
        // C# - []
        // Unlike C#, Java primitives can't use equals().  This means they always use value equality.
        int five = 5;
        int six = 6;
        assert five != six;

        int fiveAgain = 5;
        assert five == fiveAgain;

        // Java doesn't support operator overloading, so Equals() always checks for value equality and == always checks
        // for reference equality for objects.  I'd argue this is better than C#'s approach.
        String day = "Saturday the 27th";
        String dayAgain = "Saturday the 27th";
        String dayAgainAgain = new String("Saturday the 27th");

        // This is a unique case.  Java caches string literals (not created with a constructor) so that they reference
        // the same underlying object in memory.
        assert day == dayAgain;

        // When Strings are created with a constructor (like dayAgainAgain), they are not cached and reference
        // a new underlying object.
        assert day != dayAgainAgain;

        // equals() does value comparison as expected.
        assert day.equals(dayAgain);
        assert day.equals(dayAgainAgain);
    }
}
