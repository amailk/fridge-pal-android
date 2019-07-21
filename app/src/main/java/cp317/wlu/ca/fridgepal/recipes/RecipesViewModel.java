package cp317.wlu.ca.fridgepal.recipes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import cp317.wlu.ca.fridgepal.model.Recipe;
import cp317.wlu.ca.fridgepal.repositories.FoodRepository;
import cp317.wlu.ca.fridgepal.repositories.SpoonacularRepository;

public class RecipesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Recipe>> recipeLiveData = new MutableLiveData<>();
    private MutableLiveData<Recipe> selectedRecipeLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Recipe>> relatedRecipesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Recipe>> suggestedRecipesLiveData = new MutableLiveData<>();

    private final SpoonacularRepository spoonacularRepository;
    private final FoodRepository foodRepository;

    RecipesViewModel(Application application) {
        super(application);
        spoonacularRepository = new SpoonacularRepository();
        foodRepository = FoodRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipeLiveData() {
        return recipeLiveData;
    }

    public void fetchRecipes() {
        refreshRecipes();
        foodRepository.addDataLoadedListener(() -> refreshRecipes());
    }

    private void refreshRecipes() {
        List<String> ingredients = foodRepository.getCategories().stream().flatMap(x -> x.getFoods().stream()).map(x -> x.getName()).collect(Collectors.toList());
        spoonacularRepository.fetchRecipesByIngredient(ingredients, recipes -> recipeLiveData.postValue(recipes));
    }

    public LiveData<Recipe> getSelectedRecipeLiveData() {
        return selectedRecipeLiveData;
    }

    public void fetchSelectedRecipe(String recipeId) {
        spoonacularRepository.fetchRecipeInformation(recipeId, recipe -> selectedRecipeLiveData.setValue(recipe));
    }

    public void fetchRelatedRecipesForRecipe(String recipeId) {
        spoonacularRepository.fetchRelatedRecipesForRecipe(recipeId, recipes -> relatedRecipesLiveData.setValue(recipes));
    }

    public LiveData<List<Recipe>> getRelatedRecipesForRecipe() {
        return relatedRecipesLiveData;
    }

    public void fetchSuggestedRecipesForIngredient(String ingredientName) {
        spoonacularRepository.fetchRecipesByIngredient(Collections.singletonList(ingredientName), recipes -> suggestedRecipesLiveData.setValue(recipes));
    }

    public LiveData<List<Recipe>> getSuggestedRecipesForIngredient() {
        return suggestedRecipesLiveData;
    }
}
