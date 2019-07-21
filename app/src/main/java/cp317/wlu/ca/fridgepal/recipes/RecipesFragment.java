package cp317.wlu.ca.fridgepal.recipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Recipe;

public class RecipesFragment extends Fragment {

    private static final String TYPE = "TYPE";
    private static final String RECIPE_ID = "RECIPE_ID";
    private static final String INGREDIENT_NAME = "INGREDIENT_NAME";
    private static final int TYPE_RECIPES_FOR_FRIDGE = 0;
    private static final int TYPE_RELATED_RECIPES_FOR_RECIPE = 1;
    private static final int TYPE_SUGGESTED_RECIPES_FOR_INGREDIENT = 2;

    private RecipesViewModel viewModel;
    private int fragmentType;
    private String relatedRecipeId;
    private String ingredientName;

    public static RecipesFragment newInstanceWithRecipesForFood() {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, TYPE_RECIPES_FOR_FRIDGE);
        args.putString(RECIPE_ID, null);
        args.putString(INGREDIENT_NAME, null);
        fragment.setArguments(args);
        return fragment;
    }

    public static RecipesFragment newInstanceWithRelatedRecipesForRecipe(String recipeId) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, TYPE_RELATED_RECIPES_FOR_RECIPE);
        args.putString(RECIPE_ID, recipeId);
        args.putString(INGREDIENT_NAME, null);
        fragment.setArguments(args);
        return fragment;
    }

    public static RecipesFragment newInstanceWithSuggestedRecipesForIngredient(String ingredientName) {
        RecipesFragment fragment = new RecipesFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, TYPE_SUGGESTED_RECIPES_FOR_INGREDIENT);
        args.putString(RECIPE_ID, null);
        args.putString(INGREDIENT_NAME, ingredientName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentType = getArguments().getInt(TYPE, TYPE_RECIPES_FOR_FRIDGE);
        relatedRecipeId = getArguments().getString(RECIPE_ID, null);
        ingredientName = getArguments().getString(INGREDIENT_NAME, null);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipes_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView rvRecipes = view.findViewById(R.id.recycler_view);
        viewModel = ViewModelProviders.of(this).get(RecipesViewModel.class);

        LiveData<List<Recipe>> recipesLiveData = getRecipesForFragmentType();

        recipesLiveData.observe(this, recipes -> {
            RecipesAdapter adapter = new RecipesAdapter(recipes, recipe -> {
                Intent intent = new Intent(getContext(), RecipeActivity.class);
                intent.putExtra(RecipeActivity.EXTRA_RECIPE_ID, recipe.getId());
                startActivity(intent);
            });
            rvRecipes.setAdapter(adapter);
            rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        });

    }

    private LiveData<List<Recipe>> getRecipesForFragmentType() {
        switch (fragmentType) {
            case TYPE_RECIPES_FOR_FRIDGE:
                viewModel.fetchRecipes();
                return viewModel.getRecipeLiveData();
            case TYPE_RELATED_RECIPES_FOR_RECIPE:
                viewModel.fetchRelatedRecipesForRecipe(relatedRecipeId);
                return viewModel.getRelatedRecipesForRecipe();
            case TYPE_SUGGESTED_RECIPES_FOR_INGREDIENT:
                viewModel.fetchSuggestedRecipesForIngredient(ingredientName);
                return viewModel.getSuggestedRecipesForIngredient();
            default:
                viewModel.fetchRecipes();
                return viewModel.getRecipeLiveData();
        }
    }
}
