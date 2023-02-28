package adapter;

public class TankAdapter implements Unit {
    private Tank tank;
    
    public TankAdapter(Tank tank) {
        this.tank = tank;
    }
    
    @Override
    public void attack() {
        tank.fire();
    }
    
    @Override
    public void move() {
        tank.drive();
    }
}
