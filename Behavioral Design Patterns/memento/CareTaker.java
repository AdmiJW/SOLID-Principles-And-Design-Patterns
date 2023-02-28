package memento;

import java.util.Stack;

public class CareTaker {
    // Singleton
    private static CareTaker instance;
    private CareTaker() {
        mementos = new Stack<>();
    }

    public static CareTaker getInstance() {
        if (instance == null) instance = new CareTaker();
        return instance;
    }


    private Stack<MyAppStateMemento> mementos;
    
    public void addMemento(MyAppStateMemento memento) {
        mementos.push(memento);
    }

    public void undo() {
        if (mementos.isEmpty()) return;
        mementos.pop().restore();
    }
}
