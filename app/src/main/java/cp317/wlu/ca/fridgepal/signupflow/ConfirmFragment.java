package cp317.wlu.ca.fridgepal.signupflow;


import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import cp317.wlu.ca.fridgepal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment {
    TextView groceryText;
    TextView dietaryText;
    private SignupFlowViewModel viewModel;
    private onNextPressedListener onNextPressedListener;

    public ConfirmFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);

        groceryText = view.findViewById(R.id.user_groceryDay);
        dietaryText = view.findViewById(R.id.user_dietary);

        ImageView confirmImage = view.findViewById(R.id.confirm_image);
        confirmImage.setColorFilter(new PorterDuffColorFilter(getContext().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY));

        Button nextButton = view.findViewById(R.id.button4);
        nextButton.setOnClickListener(onNextPressedListener::onNextPressed);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SignupFlowViewModel.class);
        groceryText.setText(viewModel.getGroceryDay());
        dietaryText.setText(viewModel.getDietaryPreference());
    }

    public void setOnNextPressedListener(onNextPressedListener onNextPressedListener) {
        this.onNextPressedListener = onNextPressedListener;
    }

    interface onNextPressedListener {
        void onNextPressed(View view);
    }
}
