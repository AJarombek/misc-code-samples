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
    private static final String METER = "meter";
    private static final String MILE = "mile";
    private static final String KILOMETER = "kilometer";

    /**
     * Convert a value from one unit to another.  Options are: temperature, distance
     * @param unitType The original unit type
     * @param convertType The unit type to be converted to
     * @param value The original value
     * @return The converted value
     */
    public static double conversion(String unitType, String convertType, double value) {
        if (unitType.equals(FAHRENHEIT) || unitType.equals(CELSIUS) || unitType.equals(KELVIN))
            return temperatureConversion(unitType, convertType, value);
        else if (unitType.equals(METER) || unitType.equals(MILE) || unitType.equals(KILOMETER))
            return distanceConversion(unitType, convertType, value);
        else {
            System.out.println("ERROR: Invalid Unit Type: " + unitType);
            System.exit(0);
        }
        return 0;
    }

    /**
     * A helper function for distance specific conversions
     * @param unitType The original unit type
     * @param convertType The unit type to be converted to
     * @param value The original value
     * @return The converted value
     */
    private static double distanceConversion(String unitType, String convertType, double value) {
        switch (unitType) {
            case METER:
                switch (convertType) {
                    case METER:
                        return value;
                    case MILE:
                        return meterToMile(value);
                    case KILOMETER:
                        return meterToKilometer(value);
                    default:
                        System.out.println("ERROR: Invalid Convert Type: " + convertType);
                        System.exit(0);
                }
            case MILE:
                switch (convertType) {
                    case METER:
                        return mileToMeter(value);
                    case MILE:
                        return value;
                    case KILOMETER:
                        return mileToKilometer(value);
                    default:
                        System.out.println("ERROR: Invalid Convert Type: " + convertType);
                        System.exit(0);
                }
            case KILOMETER:
                switch (convertType) {
                    case METER:
                        return kilometerToMeter(value);
                    case MILE:
                        return kilometerToMile(value);
                    case KILOMETER:
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
    private static double kilometerToMile(double value) {
        return value * .6213711;
    }

    private static double kilometerToMeter(double value) {
        return value * 1000;
    }

    private static double mileToKilometer(double value) {
        return value * 1.609344;
    }

    private static double mileToMeter(double value) {
        return value * 1609.344;
    }

    private static double meterToMile(double value) {
        return value * .0006213711;
    }

    private static double meterToKilometer(double value) {
        return value / 1000;
    }

    public static double farToCel(double value) {
        return (value - 32) * (5/9);
    }

    public static double farToKel(double value) {
        return (value + 459.67) * (5/9);
    }

    public static double celToFar(double value) {
        return value * 1.8 + 32;
    }

    public static double celToKel(double value) {
        return value + 273.15;
    }

    public static double kelToFar(double value) {
        return value * 1.8 - 459.67;
    }

    public static double kelToCel(double value) {
        return value - 273.15;
    }

    public static void main(String[] args) {
        String unitType = args[0];
        String convertType = args[1];
        double value = Double.parseDouble(args[2]);

        System.out.println("The value " + value + " " + unitType + " in " +
                convertType + " is: " + conversion(unitType, convertType, value));
    }
}
