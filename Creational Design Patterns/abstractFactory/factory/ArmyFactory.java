package abstractFactory.factory;

import abstractFactory.product.Infantry;
import abstractFactory.product.Tank;


public interface ArmyFactory {
    Tank createTank();
    Infantry createInfantry();
}