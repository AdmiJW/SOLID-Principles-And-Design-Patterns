package abstractFactory;

import abstractFactory.factory.ArmyFactory;
import abstractFactory.factory.HardArmyFactory;
import abstractFactory.factory.NormalArmyFactory;
import abstractFactory.product.Infantry;
import abstractFactory.product.Tank;

public class Main {
    
    public static void main(String[] args) {

        // if (gameMode == "normal")
        ArmyFactory factory = new NormalArmyFactory();
        Infantry infantry = factory.createInfantry();
        infantry.attack();
        infantry.defend();
        Tank tank = factory.createTank();
        tank.attack();
        tank.defend();

        // else if (gameMode == "hard")
        factory = new HardArmyFactory();
        infantry = factory.createInfantry();
        infantry.attack();
        infantry.defend();
        tank = factory.createTank();
        tank.attack();
        tank.defend();
    }
}
