import java.math.BigDecimal;

/**
 * Author: Andrew Jarombek
 * Date: 5/24/2016
 * Find PI to the Nth Digit - Enter a number and have the program generate PI up to that many decimal places.
 * Keep a limit to how far the program will go.
 * [CMD] This Program uses the Command Line.
 */
public class PIDigits {

    /**
     * Find PI to the Nth Digit
     * @param digits number of digits to be represented (must be <= 15)
     * @return PI represented to the specified precision
     */
    public static double findPI(int digits) {

        // Make sure digits is in the proper range
        if (digits > 15 || digits < 0) {
            System.err.println("ERROR: Digit Value Invalid.  Must Be From 0 to 15. \nDigit Value Given: " + digits);
            System.exit(0);
        }

        double PI = Math.PI;
        return new BigDecimal(PI).setScale(digits, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        int precision = Integer.parseInt(args[0]);
        System.out.println("PI represented with " + precision + " digits: " + findPI(precision));
    }
}
