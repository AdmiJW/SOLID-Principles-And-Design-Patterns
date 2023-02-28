package abstractFactory.product;



public class RegularInfantry implements Infantry {
    private int attack;
    private int defense;

    public RegularInfantry(int attack, int defense) {
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public void attack() {
        System.out.println("Regular infantry attacks with " + attack + " points");
    }

    @Override
    public void defend() {
        System.out.println("Regular infantry defends with " + defense + " points");
    }
}
