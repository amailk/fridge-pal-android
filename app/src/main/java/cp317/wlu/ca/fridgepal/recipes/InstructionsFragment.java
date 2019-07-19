package cp317.wlu.ca.fridgepal.recipes;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.IngredientRecipe;
import cp317.wlu.ca.fridgepal.model.Recipe;

public class InstructionsFragment extends Fragment {
    private RecipesViewModel viewModel;

    public static InstructionsFragment newInstance() {
        return new InstructionsFragment();
    }

    private IngredientRecipe recipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instructions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView instructions = view.findViewById(R.id.instructions);

        viewModel = ViewModelProviders.of(getActivity()).get(RecipesViewModel.class);

        recipe = viewModel.getSelectedRecipeLiveData().getValue();

        // TODO: Get description from recipe
//        instructions.setText(recipe.getDescription());
    }
}
