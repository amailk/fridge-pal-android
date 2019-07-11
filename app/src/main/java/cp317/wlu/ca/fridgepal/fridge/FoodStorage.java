package cp317.wlu.ca.fridgepal.fridge;

import android.content.Context;

import java.util.ArrayList;

import cp317.wlu.ca.fridgepal.model.Category;
import cp317.wlu.ca.fridgepal.model.Food;

public class FoodStorage
{
    private static FoodStorage sFoodStorage;

    private ArrayList<Category> categories;

    public static FoodStorage get(Context context)
    {
        if(sFoodStorage == null)
        {
            sFoodStorage = new FoodStorage(context);
        }
        return sFoodStorage;
    }

    private FoodStorage(Context context)
    {
        categories = new ArrayList<>();

        Category meat = new Category("Meat");
        Category dairy = new Category("Dairy");
        Category fruit = new Category("Fruit");
        Category vegetable = new Category("Vegetable");

        for(int i = 0; i < 10; ++i)
        {
            Food f = new Food("Food number " + i);
            meat.addFood(f);
            dairy.addFood(f);
            fruit.addFood(f);
            vegetable.addFood(f);
        }

        categories.add(meat);
        categories.add(dairy);
        categories.add(fruit);
        categories.add(vegetable);
    }

    public ArrayList<Category> getCategories()
    {
        return categories;
    }

    public void addFood(Food food)
    {
        for(Category cats: categories)
        {
            if(cats.getCategoryName().equals(food.getFoodCategory()))
            {
                cats.addFood(food);
                break;
            }
        }
    }
}