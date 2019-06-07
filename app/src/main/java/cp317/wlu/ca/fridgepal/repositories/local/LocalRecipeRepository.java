package cp317.wlu.ca.fridgepal.repositories.local;

import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;

import java.util.Arrays;
import java.util.List;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Recipe;
import cp317.wlu.ca.fridgepal.repositories.RecipeRepository;


public class LocalRecipeRepository implements RecipeRepository {

    private final Resources resources;

    public LocalRecipeRepository(Resources resources) {
        this.resources = resources;
    }

    @Override
    public List<Recipe> getRecipes() {
        return Arrays.asList(
                new Recipe(
                        "Raspberry Pie",
                        "Preheat oven to 425 degrees F. Mix together the raspberries, sugar, lemon juice, cinnamon and salt until raspberries are well covered.",
                        ResourcesCompat.getDrawable(resources, R.drawable.raspberry, null)),
                new Recipe(
                        "Thai Fried Rice",
                        "asd",
                        ResourcesCompat.getDrawable(resources, R.drawable.raspberry, null)),
                new Recipe(
                        "Spaghetti",
                        "asda",
                        ResourcesCompat.getDrawable(resources, R.drawable.raspberry, null)),
                new Recipe(
                        "Chocolate Cake",
                        "asdasdasda",
                        ResourcesCompat.getDrawable(resources, R.drawable.raspberry, null)));
    }
}
