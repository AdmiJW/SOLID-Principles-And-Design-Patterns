package composite;

import java.util.ArrayList;
import java.util.List;

public class MenuGroup implements MenuComponent {
    private String name;
    private List<MenuComponent> menuComponents;

    public MenuGroup(String name) {
        this.name = name;
        this.menuComponents = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }
    
    @Override
    public void printMenu() {
        printMenu(0);
    }

    @Override
    public void printMenu(int level) {
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println(name);

        for (MenuComponent menuComponent : menuComponents)
            menuComponent.printMenu(level + 1);
    }
}
