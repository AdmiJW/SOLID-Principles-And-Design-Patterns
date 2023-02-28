package visitor.visitable;

import visitor.visitors.ProductDataExportVisitor;

public interface DataExportableProduct {
    public String accept(ProductDataExportVisitor visitor);
}