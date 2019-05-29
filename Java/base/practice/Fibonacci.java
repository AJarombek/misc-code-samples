import java.math.BigInteger;
import java.util.Arrays;

/**
 * Author: Andrew Jarombek
 * Date: 5/24/2016
 * Fibonacci Sequence - Enter a number and have the program generate the Fibonacci sequence
 * to that number or to the Nth number.
 * [CMD] This Program uses the Command Line.
 */
public class Fibonacci {

    /**
     * Generate a fibonacci sequence to the Nth number
     * @param number length of fibonacci sequence
     * @return String representation of a fibonacci sequence
     */
    public static String fibonacci(int number) {

        // Use BigInteger class so we can create large numbers until we run out of memory
        BigInteger[] sequence = new BigInteger[number+1];

        // Make sure the number parameter is greater than 0
        if (number < 0) {
            System.err.println("ERROR: Invalid Fibonacci Sequence Length Specified.  Length Must Be > 0.\n" +
                    "You Entered: " + number);
            System.exit(0);
        }

        // Put initial values into sequence array
        if (number >= 0)
            sequence[0] = BigInteger.ZERO;
        if (number >= 1)
            sequence[1] = BigInteger.ONE;

        // Loop until sequence array is filled with fibonacci sequence
        int count = 2;
        while (count <= number) {
            sequence[count] = sequence[count-1].add(sequence[count-2]);
            count++;
        }

        return Arrays.toString(sequence);
    }

    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        System.out.println(fibonacci(num));
    }
}
