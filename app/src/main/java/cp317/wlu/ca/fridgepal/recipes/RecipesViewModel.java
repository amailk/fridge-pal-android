package cp317.wlu.ca.fridgepal.recipes;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import cp317.wlu.ca.fridgepal.model.Recipe;
import cp317.wlu.ca.fridgepal.repositories.RecipeRepository;
import cp317.wlu.ca.fridgepal.repositories.local.LocalRecipeRepository;

public class RecipesViewModel extends AndroidViewModel {

    private final RecipeRepository recipeRepository;
    private MutableLiveData<List<Recipe>> recipeLiveData = new MutableLiveData<>();

    private MutableLiveData<Recipe> selectedRecipeLiveData = new MutableLiveData<>();

    RecipesViewModel(Application application){
        super(application);
        recipeRepository = new LocalRecipeRepository(application.getResources());
    }

    public LiveData<List<Recipe>> getRecipeLiveData() {
        return recipeLiveData;
    }

    public void fetchRecipes() {
        recipeLiveData.setValue(recipeRepository.getRecipes());
    }

    public LiveData<Recipe> getSelectedRecipeLiveData() {
        return selectedRecipeLiveData;
    }

    public void setSelectedRecipe(Recipe recipe) {
        selectedRecipeLiveData.setValue(recipe);
    }
}
