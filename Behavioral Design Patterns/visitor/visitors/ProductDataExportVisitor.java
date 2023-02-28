package visitor.visitors;

import visitor.visitable.Drink;
import visitor.visitable.Food;

public interface ProductDataExportVisitor {
    public String visit(Food food);
    public String visit(Drink drink);
}
