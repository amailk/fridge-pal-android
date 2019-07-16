package cp317.wlu.ca.fridgepal.recipes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.stream.Collectors;

import cp317.wlu.ca.fridgepal.model.IngredientRecipe;
import cp317.wlu.ca.fridgepal.repositories.FoodRepository;
import cp317.wlu.ca.fridgepal.repositories.SpoonacularRepository;

public class RecipesViewModel extends AndroidViewModel {

    private MutableLiveData<List<IngredientRecipe>> recipeLiveData = new MutableLiveData<>();

    private MutableLiveData<IngredientRecipe> selectedRecipeLiveData = new MutableLiveData<>();
    private final SpoonacularRepository spoonacularRepository;
    private final FoodRepository foodRepository;

    RecipesViewModel(Application application) {
        super(application);
        spoonacularRepository = new SpoonacularRepository();
        foodRepository = FoodRepository.getInstance();
    }

    public LiveData<List<IngredientRecipe>> getRecipeLiveData() {
        return recipeLiveData;
    }

    public void fetchRecipes() {
        refreshRecipes();
        foodRepository.addDataLoadedListener(() -> {
            refreshRecipes();
        });
    }

    private void refreshRecipes() {
        List<String> ingredients = foodRepository.getCategories().stream().flatMap(x -> x.getFoods().stream()).map(x -> x.getName()).collect(Collectors.toList());
        spoonacularRepository.fetchRecipesByIngredient(ingredients, ingredientRecipes -> recipeLiveData.postValue(ingredientRecipes));
    }

    public LiveData<IngredientRecipe> getSelectedRecipeLiveData() {
        return selectedRecipeLiveData;
    }

    public void setSelectedRecipe(IngredientRecipe recipe) {
        selectedRecipeLiveData.setValue(recipe);
    }
}
