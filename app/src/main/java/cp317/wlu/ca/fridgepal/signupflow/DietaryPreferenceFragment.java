package cp317.wlu.ca.fridgepal.signupflow;


import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import cp317.wlu.ca.fridgepal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DietaryPreferenceFragment extends Fragment {

    private SignupFlowViewModel viewModel;
    private OnNextPressedListener onNextPressedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(getActivity()).get(SignupFlowViewModel.class);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dietary_preference, container, false);

        Spinner spinner = view.findViewById(R.id.spinnerDiet);

        ImageView dietPrefImage = view.findViewById(R.id.diet_pref_image);
        dietPrefImage.setColorFilter(new PorterDuffColorFilter(getContext().getColor(R.color.secondaryDarkColor), PorterDuff.Mode.MULTIPLY));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.diet, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button nextButton = view.findViewById(R.id.button_nextC);
        nextButton.setOnClickListener(v -> {
            viewModel.setDietaryPreference(spinner.getSelectedItem().toString());
            onNextPressedListener.onNextPressed(v);
        });

        return view;
    }

    public void setOnNextPressedListener(OnNextPressedListener onNextPressedListener) {
        this.onNextPressedListener = onNextPressedListener;
    }

    public interface OnNextPressedListener {
        void onNextPressed(View view);
    }
}
