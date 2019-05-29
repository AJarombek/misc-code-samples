/**
 * @author Andrew Jarombek
 * Date: 7/19/2016
 * Calculates the distance between two cities and allows the user to specify a unit of distance.
 * [CMD] Usage: > java CityDistance [CityName1] [xCity1] [yCity1] [CityName2] [xCity2] [yCity2]
 */
public class CityDistance {

    /**
     * Calculate the distance between two city objects
     * @param city1 The first City
     * @param city2 The second City
     * @return The distance between the two cities
     */
    protected static double cityDistance(City city1, City city2) {
        double xDist = city1.getX_cor() - city2.getY_cor();
        double yDist = city1.getY_cor() - city2.getY_cor();
        // c^2 = a^2 + b^2
        double dist = Math.pow(xDist, 2) + Math.pow(yDist, 2);
        return Math.sqrt(dist);
    }

    public static void main(String[] args) {
        City city1 = new City(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        // Alternate three line approach to creating a city with x and y coordinates
        City city2 = new City(args[3]);
        city2.setX_cor(Integer.parseInt(args[4]));
        city2.setY_cor(Integer.parseInt(args[5]));
        System.out.println("The distance between " + city1.getName() + " and " + city2.getName() +
                " is: " + cityDistance(city1, city2));
    }
}
