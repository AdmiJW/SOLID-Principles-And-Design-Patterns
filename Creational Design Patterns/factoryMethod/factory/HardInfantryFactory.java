package factoryMethod.factory;

import factoryMethod.product.Infantry;
import factoryMethod.product.ShieldedInfantry;

public class HardInfantryFactory implements InfantryFactory {

    @Override
    public Infantry createInfantry() {
        return new ShieldedInfantry(20, 20, 10);
    }
}
