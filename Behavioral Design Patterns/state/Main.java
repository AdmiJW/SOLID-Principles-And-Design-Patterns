package state;

public class Main {
    public static void main(String[] args) {
        Gate gate = new Gate();
        System.out.println(gate.getDisplayMessage());
        System.out.println(gate.getDisplayLight());
        gate.enter();
        System.out.println(gate.getDisplayMessage());
        System.out.println(gate.getDisplayLight());
        gate.pay();
        System.out.println(gate.getDisplayMessage());
        System.out.println(gate.getDisplayLight());
        gate.transactionComplete();
        System.out.println(gate.getDisplayMessage());
        System.out.println(gate.getDisplayLight());
        gate.enter();
        System.out.println(gate.getDisplayMessage());
        System.out.println(gate.getDisplayLight());
    }
}
