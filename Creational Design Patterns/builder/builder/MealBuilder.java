package builder.builder;


public interface MealBuilder {
    public MealBuilder buildName();
    public MealBuilder buildBurger(String burger);
    public MealBuilder buildDrink(String drink);
    public MealBuilder buildFries(String fries);
}
