package bridge.abstractions;

import bridge.implementations.Resource;



public abstract class View {
    protected Resource resource;

    public View(Resource resource) {
        this.resource = resource;
    }

    public abstract void show();
}
