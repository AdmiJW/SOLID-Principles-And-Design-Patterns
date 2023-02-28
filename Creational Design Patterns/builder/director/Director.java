package builder.director;

import builder.builder.KFCMealBuilder;
import builder.builder.McDonaldMealBuilder;
import builder.builder.MealBuilder;
import builder.product.KFCMeal;
import builder.product.McDonaldMeal;

public class Director {
    
    public McDonaldMeal makeBigMacMeal() {
        McDonaldMealBuilder mealBuilder = new McDonaldMealBuilder();

        mealBuilder.buildName()
            .buildBurger("Big Mac")
            .buildDrink("Coke")
            .buildFries("Large Fries");

        return mealBuilder.getMeal();
    }


    public KFCMeal makeZingerMeal() {
        KFCMealBuilder mealBuilder = new KFCMealBuilder();

        mealBuilder.buildName()
            .buildBurger("Zinger")
            .buildDrink("Sprite")
            .buildFries("Medium Fries");

        return mealBuilder.getMeal();
    }

}
