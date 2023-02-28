package memento;

public class MyAppState {
    private String name;
    private String description;

    public MyAppState(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        save();
        this.name = name;
    }

    public void setDescription(String description) {
        save();
        this.description = description;
    }


    public void save() {
        MyAppStateMemento memento = new MyAppStateMemento(this, name, description);
        CareTaker.getInstance().addMemento(memento);
    }

    public void restore(MyAppStateMemento memento) {
        this.name = memento.getName();
        this.description = memento.getDescription();
    }
}