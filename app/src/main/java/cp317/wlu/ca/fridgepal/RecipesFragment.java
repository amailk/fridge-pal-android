package cp317.wlu.ca.fridgepal;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cp317.wlu.ca.fridgepal.model.Recipe;

public class RecipesFragment extends Fragment {

    private RecipesViewModel viewModel;

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
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

        viewModel.getRecipeLiveData().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                String recipeString = "";
                String recipeDesc = "";
                Resources res = getContext().getResources();
                Drawable recipeImage = ResourcesCompat.getDrawable(res, R.drawable.raspberry, null);

                for (Recipe recipe : recipes) {
                    recipeString += recipe.getName() + "\n";
                    recipeDesc += recipe.getDescription() + "\n";

                }
                RecipesAdapter adapter = new RecipesAdapter(recipes, getContext());
                rvRecipes.setAdapter(adapter);
                rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        viewModel.fetchRecipes();

    }

}
