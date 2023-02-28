package memento;

public class MyAppStateMemento {
    private MyAppState originator;

    private String name;
    private String description;

    public MyAppStateMemento(MyAppState originator, String name, String description) {
        this.originator = originator;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void restore() {
        originator.restore(this);
    }
}
