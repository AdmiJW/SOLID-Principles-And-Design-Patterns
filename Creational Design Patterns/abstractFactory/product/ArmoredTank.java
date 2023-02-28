package abstractFactory.product;


public class ArmoredTank implements Tank {
    private int attack;
    private int defense;
    private int armor;

    public ArmoredTank(int attack, int defense, int armor) {
        this.attack = attack;
        this.defense = defense;
        this.armor = armor;
    }

    @Override
    public void attack() {
        System.out.println("Armored tank attacks with " + attack + " points");
    }

    @Override
    public void defend() {
        System.out.println("Armored tank defends with " + defense + " points");
        System.out.println("Armored tank defends with " + armor + " points");
    }
}