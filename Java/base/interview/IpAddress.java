/**
 * Interview practice problem.
 * Description:
 *     Given a valid (IPv4) IP address, return a defanged version of that IP address.
 *     A defanged IP address replaces every period "." with "[.]".
 *
 * @author Andrew Jarombek
 * @since 10/13/2019
 */

public class IpAddress {

    /**
     * Transform an IP address by placing each period within brackets.
     * @param address The initial IP address to transform.
     * @return A String representing the new IP address.
     */
    private static String defangIPaddr(String address) {
        StringBuilder ip = new StringBuilder();

        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            int ascii = c;

            // Character codes 0-9 range from 48 to 57.
            if (ascii >= 48 && ascii <= 57) {
                ip.append(c);
            } else {
                ip.append('[');
                ip.append(c);
                ip.append(']');
            }
        }

        return ip.toString();
    }

    public static void main(String... args) {
        String result1 = defangIPaddr("0.0.0.0");
        assert result1.equals("0[.]0[.]0[.]0");

        String result2 = defangIPaddr("127.0.0.1");
        assert result2.equals("127[.]0[.]0[.]1");
    }
}
