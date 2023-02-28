package adapter;

public class Main {
    public static void main(String[] args) {
        Infantry infantry = new Infantry();
        Tank tank = new Tank();
        TankAdapter tankAdapter = new TankAdapter(tank);
        
        infantry.attack();
        infantry.move();
        
        tankAdapter.attack();
        tankAdapter.move();
    }
}
