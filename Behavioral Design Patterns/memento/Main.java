package memento;

public class Main {
    public static void main(String[] args) {
        MyAppState state = new MyAppState("Name1", "Description1");
        System.out.println(state.getName());
        System.out.println(state.getDescription());

        state.setName("Name2");
        state.setDescription("Description2");

        System.out.println(state.getName());
        System.out.println(state.getDescription());

        CareTaker.getInstance().undo();

        System.out.println(state.getName());
        System.out.println(state.getDescription());

        CareTaker.getInstance().undo();

        System.out.println(state.getName());
        System.out.println(state.getDescription());
    }
}
