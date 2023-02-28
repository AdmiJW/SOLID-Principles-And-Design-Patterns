package builder.product;

public class KFCMeal implements Meal {
    private String name;
    private String burger;
    private String drink;
    private String fries;

    public void setName(String name) {
        this.name = name;
    }

    public void setBurger(String burger) {
        this.burger = burger;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setFries(String fries) {
        this.fries = fries;
    }

    @Override
    public void showItems() {
        System.out.println("Name: " + name);
        System.out.println("Burger: " + burger);
        System.out.println("Drink: " + drink);
        System.out.println("Fries: " + fries);
    }


}
