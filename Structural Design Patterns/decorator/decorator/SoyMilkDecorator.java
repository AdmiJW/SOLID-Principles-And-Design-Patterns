package decorator.decorator;

import decorator.beverage.Beverage;


public class SoyMilkDecorator extends BeverageDecorator {
    public SoyMilkDecorator(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + Soy milk";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 0.5;
    }
}