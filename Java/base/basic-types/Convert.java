/**
 * Converting between basic types in Java.
 * @author Andrew Jarombek
 * @since 7/20/2019
 */

class Convert {

    static void Execute() {
        // Convert a string to an integer
        var year = Integer.parseInt("2019");
        assert year == 2019;

        // Parse a hexadecimal number
        // C# - []
        var twentySix = Integer.parseInt("1A", 16);
        assert twentySix == 26;
    }
}
