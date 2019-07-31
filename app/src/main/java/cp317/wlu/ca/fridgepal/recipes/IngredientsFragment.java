package cp317.wlu.ca.fridgepal.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cp317.wlu.ca.fridgepal.R;

public class IngredientsFragment extends Fragment {

    private RecipesViewModel viewModel;

    public static IngredientsFragment newInstance() {
        return new IngredientsFragment();
    }

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
        final RecyclerView ingredientsRecyclerView = view.findViewById(R.id.ingredient_recycler_view);

        viewModel.getSelectedRecipeLiveData().observe(this, recipe -> {
            IngredientsAdapter adapter = new IngredientsAdapter(recipe.getExtendedIngredients());
            ingredientsRecyclerView.setAdapter(adapter);
            ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });

    }
}
