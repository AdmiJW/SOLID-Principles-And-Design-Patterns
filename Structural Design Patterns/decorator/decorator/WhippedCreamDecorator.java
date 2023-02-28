package decorator.decorator;

import decorator.beverage.Beverage;


public class WhippedCreamDecorator extends BeverageDecorator {
    public WhippedCreamDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + Whipped cream";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.5;
    }
}
