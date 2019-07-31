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

public class InstructionsFragment extends Fragment {
    private RecipesViewModel viewModel;

    public static InstructionsFragment newInstance() {
        return new InstructionsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instructions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(getActivity()).get(RecipesViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        viewModel.getSelectedRecipeLiveData().observe(this, recipe -> {
            if (!recipe.getAnalyzedInstructions().isEmpty()) {
                InstructionsAdapter adapter = new InstructionsAdapter(recipe.getAnalyzedInstructions().get(0).getSteps());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
    }
}
