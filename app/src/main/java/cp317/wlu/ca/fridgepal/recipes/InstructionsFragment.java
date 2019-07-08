package cp317.wlu.ca.fridgepal.recipes;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Recipe;

public class InstructionsFragment extends Fragment {
    private RecipesViewModel viewModel;

    public static InstructionsFragment newInstance() {
        return new InstructionsFragment();
    }

    private Recipe recipe;

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

        instructions.setText(recipe.getDescription());
    }
}
