package visitor.visitors;

import visitor.visitable.Drink;
import visitor.visitable.Food;



public class JSONProductDataExportVisitor implements ProductDataExportVisitor {
    @Override
    public String visit(Food food) {
        return "{ \"name\": \"" + food.getName() + "\", \"price\": " + food.getPrice() + " }";
    }

    @Override
    public String visit(Drink drink) {
        return "{ \"name\": \"" + drink.getName() + "\", \"price\": " + drink.getPrice() + " }";
    }
}
