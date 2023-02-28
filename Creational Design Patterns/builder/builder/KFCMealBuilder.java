package builder.builder;

import builder.product.KFCMeal;

public class KFCMealBuilder implements MealBuilder {
    private KFCMeal meal;

    public KFCMealBuilder() {
        meal = new KFCMeal();
    }

    @Override
    public MealBuilder buildName() {
        meal.setName("KFC Meal");
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

    public KFCMeal getMeal() {
        return meal;
    }
}
