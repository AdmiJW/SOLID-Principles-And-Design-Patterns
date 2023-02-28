package abstractFactory.product;



public class ShieldedInfantry implements Infantry {
    private int attack;
    private int defense;
    private int shield;

    public ShieldedInfantry(int attack, int defense, int shield) {
        this.attack = attack;
        this.defense = defense;
        this.shield = shield;
    }

    @Override
    public void attack() {
        System.out.println("Shielded infantry attacks with " + attack + " points");
    }

    @Override
    public void defend() {
        System.out.println("Shielded infantry defends with " + defense + " points");
        System.out.println("Shielded infantry shields with " + shield + " points");
    }    
}
