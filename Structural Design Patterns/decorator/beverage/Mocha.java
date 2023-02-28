package decorator.beverage;

public class Mocha implements Beverage {
    public String getDescription() {
        return "Mocha";
    }
    
    public double getCost() {
        return 5.0;
    }
}
