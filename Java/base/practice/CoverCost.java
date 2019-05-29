import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Author: Andrew Jarombek
 * Date: 5/28/2016
 * Find Cost of Tile to Cover W x H Floor - Calculate the total cost of tile it would take to cover a
 * floor plan of width and height, using a cost entered by the user.
 */
public class CoverCost {

    /**
     * Determine the cost of a flooring plan
     * @param width width of the floor
     * @param height height of the floor
     * @param cost cost for one square foot
     * @return the total cost for the floor plan
     */
    public static double determineCost(int width, int height, double cost) {
        int sqft = width * height;
        return cost * sqft;
    }

    public static void main(String[] args) {
        System.out.println("Enter the Cost per square inch: ");
        System.out.print('$');
        Scanner scanner = new Scanner(System.in);
        double cost = scanner.nextDouble();

        System.out.println("Enter the floor width: ");
        int width = scanner.nextInt();

        System.out.println("Enter the floor height: ");
        int length = scanner.nextInt();

        // Format for any dollar amount
        String pattern = "###.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        // Print formatted result
        System.out.println("The Cost Will Be $" + decimalFormat.format(determineCost(width, length, cost)));
    }
}
