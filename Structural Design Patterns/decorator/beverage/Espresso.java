package decorator.beverage;

public class Espresso implements Beverage {
    public String getDescription() {
        return "Espresso";
    }
    
    public double getCost() {
        return 10.0;
    }
}