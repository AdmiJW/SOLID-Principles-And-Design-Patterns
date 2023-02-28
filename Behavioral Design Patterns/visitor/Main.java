package visitor;

import visitor.visitable.Drink;
import visitor.visitable.Food;
import visitor.visitors.JSONProductDataExportVisitor;
import visitor.visitors.XMLProductDataExportVisitor;

public class Main {

    public static void main(String[] args) {
        Food food = new Food("Pizza", 10);
        Drink drink = new Drink("Coca Cola", 5);        

        JSONProductDataExportVisitor jsonVisitor = new JSONProductDataExportVisitor();
        XMLProductDataExportVisitor xmlVisitor = new XMLProductDataExportVisitor();

        System.out.println(food.accept(jsonVisitor));
        System.out.println(drink.accept(jsonVisitor));
        System.out.println(food.accept(xmlVisitor));
        System.out.println(drink.accept(xmlVisitor));
    }
    
}
