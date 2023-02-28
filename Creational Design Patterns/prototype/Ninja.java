package prototype;

public class Ninja implements Cloneable {
    private int health;

    public Ninja(int health) {
        this.health = health;
    }

    public void damage(int damage) {
        health -= damage;
    }

    public void showStats() {
        System.out.println("Ninja's Health: " + health);
    }

    @Override
    public Ninja clone() {
        return new Ninja(health);
    }
}
