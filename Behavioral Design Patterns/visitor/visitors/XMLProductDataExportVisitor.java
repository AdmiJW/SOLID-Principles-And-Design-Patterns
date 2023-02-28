package visitor.visitors;

import visitor.visitable.Drink;
import visitor.visitable.Food;


public class XMLProductDataExportVisitor implements ProductDataExportVisitor {
    @Override
    public String visit(Food food) {
        return "<food><name>" + food.getName() + "</name><price>" + food.getPrice() + "</price></food>";
    }

    @Override
    public String visit(Drink drink) {
        return "<drink><name>" + drink.getName() + "</name><price>" + drink.getPrice() + "</price></drink>";
    }
}