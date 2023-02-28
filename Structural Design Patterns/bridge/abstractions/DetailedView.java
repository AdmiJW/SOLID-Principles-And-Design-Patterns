package bridge.abstractions;

import bridge.implementations.Resource;

public class DetailedView extends View {
    
    public DetailedView(Resource resource) {
        super(resource);
    }
    
    @Override
    public void show() {
        System.out.println("Detailed View");
        System.out.println(resource.getTitle());
        System.out.println(resource.getShortDescription());
        System.out.println(resource.getLongDescription());
        System.out.println("");
    }
}