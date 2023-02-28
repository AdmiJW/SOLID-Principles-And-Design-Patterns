package builder;

import builder.director.Director;

public class Main {
    public static void main(String[] args) {
        Director director = new Director();

        director.makeBigMacMeal().showItems();
        director.makeZingerMeal().showItems();
    }    
}
