package cp317.wlu.ca.fridgepal.recipes;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Recipe;

public class IngredientsFragment extends Fragment {

    public static IngredientsFragment newInstance() {
        return new IngredientsFragment();
    }

    private Recipe recipe;
    private RecipesViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(RecipesViewModel.class);
        recipe = viewModel.getSelectedRecipeLiveData().getValue();

        final RecyclerView ingredientsRecyclerView = view.findViewById(R.id.ingredient_recycler_view);

        IngredientsAdapter adapter = new IngredientsAdapter(recipe.getIngredients(), getContext());
        ingredientsRecyclerView.setAdapter(adapter);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}