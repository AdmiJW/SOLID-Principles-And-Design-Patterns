package abstractFactory.product;


public class RegularTank implements Tank {
    private int attack;
    private int defense;

    public RegularTank(int attack, int defense) {
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public void attack() {
        System.out.println("Regular tank attacks with " + attack + " points");
    }

    @Override
    public void defend() {
        System.out.println("Regular tank defends with " + defense + " points");
    }
}