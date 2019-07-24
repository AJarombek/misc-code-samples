import java.nio.ByteBuffer;
import java.util.Arrays;

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
        // C# - [https://bit.ly/30IJVT3]
        var twentySix = Integer.parseInt("1A", 16);
        assert twentySix == 26;

        // Ways to convert an integer to a byte array.  The first way uses the unsigned right shift operator.
        // We want to shift over the sign bit just like all the other bits.
        // https://www.tutorialspoint.com/Bitwise-right-shift-operator-in-Java
        // C# - [https://bit.ly/2Y2tnZl]
        byte[] twentySixInBytes = new byte[] {
                (byte)(twentySix >>> 24),
                (byte)(twentySix >>> 16),
                (byte)(twentySix >>> 8),
                (byte)(twentySix)
        };

        byte[] yearInBytes = new byte[] {
                (byte)(year >>> 24),
                (byte)(year >>> 16),
                (byte)(year >>> 8),
                (byte)(year)
        };

        // java.nio.ByteBuffer offers a more declarative approach
        byte[] yearInBytesTwo = ByteBuffer.allocate(4).putInt(year).array();

        String twentySixByteString = Arrays.toString(twentySixInBytes);
        String yearByteString = Arrays.toString(yearInBytes);
        String yearByteStringTwo = Arrays.toString(yearInBytesTwo);

        assert twentySixByteString.equals("[0, 0, 0, 26]");

        // Certain bytes are negative since Java treats bytes as signed integers - https://stackoverflow.com/a/9609437
        assert yearByteString.equals("[0, 0, 7, -29]");
        assert yearByteString.equals(yearByteStringTwo);
    }
}
