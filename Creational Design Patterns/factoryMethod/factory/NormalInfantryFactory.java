package factoryMethod.factory;

import factoryMethod.product.Infantry;
import factoryMethod.product.RegularInfantry;

public class NormalInfantryFactory implements InfantryFactory {
    @Override
    public Infantry createInfantry() {
        return new RegularInfantry(10, 10);
    }
}
