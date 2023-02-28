package factoryMethod;

import factoryMethod.factory.HardInfantryFactory;
import factoryMethod.factory.InfantryFactory;
import factoryMethod.factory.NormalInfantryFactory;
import factoryMethod.product.Infantry;

public class Main {

    public static void main(String[] args) {

        // if (gameMode == "normal")
        InfantryFactory factory = new NormalInfantryFactory();
        Infantry infantry = factory.createInfantry();
        infantry.attack();
        infantry.defend();

        // else if (gameMode == "hard")
        factory = new HardInfantryFactory();
        infantry = factory.createInfantry();
        infantry.attack();
        infantry.defend();
    }

}
