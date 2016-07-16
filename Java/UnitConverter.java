/**
 * @author Andrew Jarombek
 * Date: 7/16/2016
 * Converts various units between one another. The user enters the type of unit being entered,
 * the type of unit they want to convert to and then the value. The program will then make the conversion.
 * [CMD] Run As: > java UnitConverter [UnitType] [ConvertToType] [Value]
 */
public class UnitConverter {

    private static final String FAHRENHEIT = "fahrenheit";
    private static final String CELSIUS = "celsius";
    private static final String KELVIN = "kelvin";

    /**
     * Convert a value from one unit to another.  Options are: temperature, distance, volume
     * @param unitType The original unit type
     * @param convertType The unit type to be converted to
     * @param value The original value
     * @return The converted value
     */
    public static double conversion(String unitType, String convertType, double value) {
        if (unitType.equals(FAHRENHEIT) || unitType.equals(CELSIUS) || unitType.equals(KELVIN))
            return temperatureConversion(unitType, convertType, value);
        else
            return 0;
    }

    /**
     * A helper function for temperature specific conversions
     * @param unitType The original unit type
     * @param convertType The unit type to be converted to
     * @param value The original value
     * @return The converted value
     */
    private static double temperatureConversion(String unitType, String convertType, double value) {
        switch (unitType) {
            case FAHRENHEIT:
                switch (convertType) {
                    case FAHRENHEIT:
                        return value;
                    case CELSIUS:
                        return farToCel(value);
                    case KELVIN:
                        return farToKel(value);
                    default:
                        System.out.println("ERROR: Invalid Convert Type: " + convertType);
                        System.exit(0);
                }
            case CELSIUS:
                switch (convertType) {
                    case FAHRENHEIT:
                        return celToFar(value);
                    case CELSIUS:
                        return value;
                    case KELVIN:
                        return celToKel(value);
                    default:
                        System.out.println("ERROR: Invalid Convert Type: " + convertType);
                        System.exit(0);
                }
            case KELVIN:
                switch (convertType) {
                    case FAHRENHEIT:
                        return kelToFar(value);
                    case CELSIUS:
                        return kelToCel(value);
                    case KELVIN:
                        return value;
                    default:
                        System.out.println("ERROR: Invalid Convert Type: " + convertType);
                        System.exit(0);
                }
            default:
                System.out.println("Error: Unknown");
                System.exit(0);
        }
        return 0;
    }

    private static double farToCel(double value) {

    }

    private static double farToKel(double value) {

    }

    private static double celToFar(double value) {

    }

    private static double celToKel(double value) {

    }

    private static double kelToFar(double value) {

    }

    private static double kelToCel(double value) {

    }

    public static void main(String[] args) {
        String unitType = args[0];
        String convertType = args[1];
        double value = Double.parseDouble(args[2]);

        System.out.println("The value " + value + " " + unitType + " in " +
                convertType + " is: " + conversion(unitType, convertType, value));
    }
}
