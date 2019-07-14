package cp317.wlu.ca.fridgepal.model;

import java.util.ArrayList;

public class Category
{
    private ArrayList<Food> foods;
    private String categoryName;

    public Category(String name)
    {
        categoryName = name;
        foods = new ArrayList<>();
        foods.clear();
    }
    public String getCategoryName()
    {
        return categoryName;
    }
    public void addFood(Food food)
    {
        foods.add(food);
    }
    public ArrayList<Food> getFoods()
    {
        return foods;
    }

    public void clear() {
        foods.clear();
    }
}