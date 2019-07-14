package cp317.wlu.ca.fridgepal.fridge;

import java.util.ArrayList;

import cp317.wlu.ca.fridgepal.model.Category;
import cp317.wlu.ca.fridgepal.model.Food;

public class FoodStorage {
    private static FoodStorage sFoodStorage;

    private ArrayList<Category> categories;

    public static FoodStorage getInstance() {
        if (sFoodStorage == null) {
            sFoodStorage = new FoodStorage();
        }
        return sFoodStorage;
    }

    private FoodStorage() {
        categories = new ArrayList<>();

        Category meat = new Category("Meat");
        Category dairy = new Category("Dairy");
        Category fruit = new Category("Fruit");
        Category vegetable = new Category("Vegetable");

        meat.addFood(new Food("Chicken Breast"));
        meat.addFood(new Food("Bacon"));
        meat.addFood(new Food("Ground Beef"));

        dairy.addFood(new Food("Butter"));
        dairy.addFood(new Food("Milk"));


        fruit.addFood(new Food("Apple"));
        fruit.addFood(new Food("Orange"));

        vegetable.addFood(new Food("Broccoli"));
        vegetable.addFood(new Food("Cucumber"));

        categories.add(meat);
        categories.add(dairy);
        categories.add(fruit);
        categories.add(vegetable);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void addFood(Food food) {
        for (Category cats : categories) {
            if (cats.getCategoryName().equals(food.getFoodCategory())) {
                cats.addFood(food);
                break;
            }
        }
    }
}