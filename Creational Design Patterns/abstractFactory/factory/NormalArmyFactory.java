package abstractFactory.factory;

import abstractFactory.product.Infantry;
import abstractFactory.product.RegularInfantry;
import abstractFactory.product.RegularTank;
import abstractFactory.product.Tank;


public class NormalArmyFactory implements ArmyFactory {
    @Override
    public Tank createTank() {
        return new RegularTank( 10, 30 );
    }

    @Override
    public Infantry createInfantry() {
        return new RegularInfantry( 10, 10 );
    }
}
