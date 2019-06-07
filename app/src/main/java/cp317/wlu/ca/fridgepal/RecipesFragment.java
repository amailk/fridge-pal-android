package cp317.wlu.ca.fridgepal;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import cp317.wlu.ca.fridgepal.model.Recipe;
import cp317.wlu.ca.fridgepal.repositories.RecipeRepository;
import cp317.wlu.ca.fridgepal.repositories.local.LocalRecipeRepository;

public class RecipesFragment extends Fragment {

    private RecipesViewModel viewModel;
    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    ArrayList<Recipe> recipes;

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

        viewModel.getRecipeLiveData().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                String recipeString = "";
                for(Recipe recipe : recipes) {
                    recipeString += recipe.getName() + "\n";
                }
                RecipesAdapter adapter = new RecipesAdapter(recipes);
                rvRecipes.setAdapter(adapter);
                rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        viewModel.fetchRecipes();

    }
}
