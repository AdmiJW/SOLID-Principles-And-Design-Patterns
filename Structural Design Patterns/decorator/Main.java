package decorator;

import decorator.beverage.Beverage;
import decorator.beverage.Espresso;
import decorator.beverage.Mocha;
import decorator.decorator.SoyMilkDecorator;
import decorator.decorator.WhippedCreamDecorator;

public class Main {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.getCost());

        Beverage beverage2 = new Espresso();
        beverage2 = new SoyMilkDecorator(beverage2);
        beverage2 = new WhippedCreamDecorator(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.getCost());

        Beverage beverage3 = new Mocha();
        beverage3 = new SoyMilkDecorator(beverage3);
        beverage3 = new SoyMilkDecorator(beverage3);
        beverage3 = new WhippedCreamDecorator(beverage3);
        System.out.println(beverage3.getDescription() + " $" + beverage3.getCost());
    }    
}
