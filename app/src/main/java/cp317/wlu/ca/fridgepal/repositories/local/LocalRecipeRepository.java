package cp317.wlu.ca.fridgepal.repositories.local;

import java.util.Arrays;
import java.util.List;

import cp317.wlu.ca.fridgepal.model.Recipe;
import cp317.wlu.ca.fridgepal.repositories.RecipeRepository;

public class LocalRecipeRepository implements RecipeRepository {
    @Override
    public List<Recipe> getRecipes() {
        return Arrays.asList(new Recipe("Raspberry Pie"), new Recipe("Thai Fried Rice"), new Recipe("Spaghetti"), new Recipe("Chocolate Cake"));
    }
}
