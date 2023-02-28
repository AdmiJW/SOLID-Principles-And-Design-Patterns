package composite;

public class MenuChild implements MenuComponent {
    private String name;

    public MenuChild(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public void printMenu() {
        printMenu(0);
    }

    @Override
    public void printMenu(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println(name);
    }
}
