package abstractFactory.factory;


import abstractFactory.product.ArmoredTank;
import abstractFactory.product.Infantry;
import abstractFactory.product.ShieldedInfantry;
import abstractFactory.product.Tank;


public class HardArmyFactory implements ArmyFactory {
    @Override
    public Tank createTank() {
        return new ArmoredTank( 20, 50, 20 );
    }

    @Override
    public Infantry createInfantry() {
        return new ShieldedInfantry( 20, 20, 10 );
    }
}