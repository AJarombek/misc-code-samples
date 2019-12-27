import java.util.ArrayList;

/**
 * Author: Andrew Jarombek
 * Date: 7/14/2016
 * The user enters a cost and then the amount of money given. The program will figure out the change
 * and the number of quarters, dimes, nickels, pennies needed for the change.
 */
public class ChangeReturn {

    public static final double QUARTER = .25;
    public static final double DIME = .10;
    public static final double NICKEL = .05;
    public static final double PENNY = .01;

    /**
     * Return a list of all the change returned
     * @param cost the cost of the item
     * @param given the amount of money given
     * @return the pieces of change returned
     */
    public static ArrayList<Double> change(double cost, double given) {
        ArrayList<Double> returnedChange = new ArrayList<>();
        // Since floating point subtraction is inaccurate, multiply by 100 for accurate results
        return change(returnedChange, (given - cost) * 100);
    }

    /**
     * A helper function for change
     * @param changeReturned the list of pieces of change returned
     * @param changeLeft the amount of change that still needs to be added to the list
     * @return the full list of change returned
     */
    private static ArrayList<Double> change(ArrayList<Double> changeReturned, double changeLeft) {
        if (changeLeft >= 25) {
            changeReturned.add(QUARTER);
            changeLeft -= 25;
            change(changeReturned, changeLeft);
        } else if (changeLeft >= 10) {
            changeReturned.add(DIME);
            changeLeft -= 10;
            change(changeReturned, changeLeft);
        } else if (changeLeft >= 5) {
            changeReturned.add(NICKEL);
            changeLeft -= 5;
            change(changeReturned, changeLeft);
        } else if (changeLeft >= 1) {
            changeReturned.add(PENNY);
            changeLeft -= 1;
            change(changeReturned, changeLeft);
        }

        return changeReturned;
    }

    public static void main(String[] args) {
        double cost = Double.parseDouble(args[0]);
        double moneyGiven = Double.parseDouble(args[1]);
        ArrayList<Double> moneyBack = change(cost, moneyGiven);
        System.out.println("Change Returned: " + moneyBack);

        // Get the total amount returned for each individual coin
        int quarter, nickel, dime, penny;
        quarter = nickel = dime = penny = 0;
        for (double coin : moneyBack) {
            if (coin == .25)
                quarter++;
            else if (coin == .1)
                dime++;
            else if (coin == .05)
                nickel++;
            else
                penny++;
        }
        System.out.println("# of Quarters: " + quarter);
        System.out.println("# of Dimes: " + dime);
        System.out.println("# of Nickels: " + nickel);
        System.out.println("# of Pennies: " + penny);
    }
}
