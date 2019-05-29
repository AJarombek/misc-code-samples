/**
 * @author Andrew Jarombek
 * Date: 7/19/2016
 * Create a city object to help find distances between cities
 */
public class City {

    private String name;
    private double x_cor;
    private double y_cor;
    private int population;

    public City(String name) {
        this.name = name;
    }

    public City(String name, double x_cor, double y_cor) {
        this.name = name;
        this.x_cor = x_cor;
        this.y_cor = y_cor;
    }

    /*
     * Getters and Setters for the City object
     */
    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX_cor() {
        return x_cor;
    }

    public void setX_cor(double x_cor) {
        this.x_cor = x_cor;
    }

    public double getY_cor() {
        return y_cor;
    }

    public void setY_cor(double y_cor) {
        this.y_cor = y_cor;
    }
}
