/**
 * @author Andrew Jarombek
 * Date: 7/14/2016
 * Develop a converter to convert a decimal number to binary or a binary number to its decimal equivalent.
 * [CMD] Run As: > java BinaryDecimal [DecimalNumber] [BinaryNumber]
 */
public class BinaryDecimal {

    /**
     * Convert a Decimal number to its Binary form
     * @param num a Decimal number
     * @return a binary number that represents the decimal argument
     */
    public static int toBinary(int num) {
        String binaryNum = "";
        while (num > 0) {
            binaryNum = (num % 2 == 1) ? 1 + binaryNum : 0 + binaryNum;
            num /= 2;
        }
        return Integer.parseInt(binaryNum);
    }

    /**
     * Convert a Binary number to its Decimal form
     * @param bin a Binary number
     * @return a decimal number that represents the binary argument
     */
    public static int fromBinary(long bin) {
        String binString = String.valueOf(bin);
        int length = binString.length();
        int decimal = 0;
        for (int i = 0; i < length; i++) {
            if (binString.charAt(length-i-1) == '1')
                decimal += Math.pow(2, i);
        }
        return decimal;
    }

    public static void main(String[] args) {
        int decimal = Integer.parseInt(args[0]);
        long binary = Long.parseLong(args[1]);
        System.out.println("The decimal number " + decimal + " in binary is " + toBinary(decimal));
        System.out.println("The binary number " + binary + " in decimal is " + fromBinary(binary));
    }
}
