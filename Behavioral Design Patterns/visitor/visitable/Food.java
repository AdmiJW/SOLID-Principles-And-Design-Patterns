package visitor.visitable;

import visitor.visitors.ProductDataExportVisitor;

public class Food implements DataExportableProduct {
    private double price;
    private String name;
    
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getName() {
        return name;
    }
    

    @Override
    public String accept(ProductDataExportVisitor visitor) {
        return visitor.visit(this);
    }
}
