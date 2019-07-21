package cp317.wlu.ca.fridgepal.signupflow;
import cp317.wlu.ca.fridgepal.R;

import androidx.lifecycle.ViewModelProviders;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroceryDayFragment extends Fragment {

    public interface OnNextPressedListener {
        void onNextPressed(View view);
    }

    private SignupFlowViewModel viewModel;
    private OnNextPressedListener onNextPressedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(getActivity()).get(SignupFlowViewModel.class);

        View view = inflater.inflate(R.layout.fragment_grocery_day, container, false);
        Spinner spinner = view.findViewById(R.id.spinner);

        ImageView groceryDayImage = view.findViewById(R.id.grocery_day_image);
        groceryDayImage.setColorFilter(new PorterDuffColorFilter(getContext().getColor(R.color.secondaryDarkColor), PorterDuff.Mode.MULTIPLY));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button nextButton = view.findViewById(R.id.button_next);
        nextButton.setOnClickListener(v -> {
            viewModel.setGroceryDay(spinner.getSelectedItem().toString());
            onNextPressedListener.onNextPressed(v);
        });

        return view;
    }

    public void setOnNextPressedListener(OnNextPressedListener onNextPressedListener) {
        this.onNextPressedListener = onNextPressedListener;
    }
}
