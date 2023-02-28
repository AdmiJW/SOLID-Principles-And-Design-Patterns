package bridge.abstractions;

import bridge.implementations.Resource;

public class SimpleView extends View {
    
    public SimpleView(Resource resource) {
        super(resource);
    }
    
    @Override
    public void show() {
        System.out.println("Simple View");
        System.out.println(resource.getTitle());
        System.out.println(resource.getShortDescription());
        System.out.println("");
    }
}
