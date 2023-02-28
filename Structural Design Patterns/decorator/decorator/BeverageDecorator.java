package decorator.decorator;

import decorator.beverage.Beverage;


public abstract class BeverageDecorator implements Beverage {
    protected Beverage beverage;

    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public abstract String getDescription();

    @Override
    public abstract double getCost();
}