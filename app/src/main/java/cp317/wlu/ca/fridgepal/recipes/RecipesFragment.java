package cp317.wlu.ca.fridgepal.recipes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Recipe;
import cp317.wlu.ca.fridgepal.repositories.SpoonacularRepository;

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

        viewModel.getRecipeLiveData().observe(this, recipes -> {
            RecipesAdapter adapter = new RecipesAdapter(recipes, getContext(), recipe -> {
//                Intent intent = new Intent(getContext(), RecipeActivity.class);
//                intent.putExtra(RecipeActivity.EXTRA_RECIPE, recipe);
//                startActivity(intent);
            });
            rvRecipes.setAdapter(adapter);
            rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        });

        viewModel.fetchRecipes();
    }
}
