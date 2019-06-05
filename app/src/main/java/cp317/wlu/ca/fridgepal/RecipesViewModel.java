package cp317.wlu.ca.fridgepal;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import cp317.wlu.ca.fridgepal.model.Recipe;
import cp317.wlu.ca.fridgepal.repositories.RecipeRepository;
import cp317.wlu.ca.fridgepal.repositories.local.LocalRecipeRepository;

public class RecipesViewModel extends ViewModel {

    private final RecipeRepository recipeRepository;
    private MutableLiveData<List<Recipe>> recipeLiveData = new MutableLiveData<>();

    public RecipesViewModel(){
        recipeRepository = new LocalRecipeRepository();
    }

    public LiveData<List<Recipe>> getRecipeLiveData() {
        return recipeLiveData;
    }
}
