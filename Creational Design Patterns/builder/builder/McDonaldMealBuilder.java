package builder.builder;

import builder.product.McDonaldMeal;


public class McDonaldMealBuilder implements MealBuilder {
    private McDonaldMeal meal;

    public McDonaldMealBuilder() {
        meal = new McDonaldMeal();
    }

    @Override
    public MealBuilder buildName() {
        meal.setName("McDonald Meal");
        return this;
    }

    @Override
    public MealBuilder buildBurger(String burger) {
        meal.setBurger(burger);
        return this;
    }

    @Override
    public MealBuilder buildDrink(String drink) {
        meal.setDrink(drink);
        return this;
    }

    @Override
    public MealBuilder buildFries(String fries) {
        meal.setFries(fries);
        return this;
    }

    public McDonaldMeal getMeal() {
        return meal;
    }    
}
