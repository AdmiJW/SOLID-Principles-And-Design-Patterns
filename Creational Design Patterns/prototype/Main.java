package prototype;


public class Main {
    public static void main(String[] args) {

        Ninja n1 = new Ninja(100);
        n1.damage(20);

        Ninja n2 = n1.clone();  // n2 is a clone of n1, so it has the same health as n1
        n2.showStats();
    }    
}
