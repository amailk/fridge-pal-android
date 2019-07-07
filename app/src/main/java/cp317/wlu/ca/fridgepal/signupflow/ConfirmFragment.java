package cp317.wlu.ca.fridgepal.signupflow;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cp317.wlu.ca.fridgepal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment {
    TextView groceryText;
    TextView dietaryText;
    private ConfirmViewModel confirm;

    public ConfirmFragment() {
        // Required empty public constructor
    }

    interface onNextPressedListener {
        void onNextPressed(View view);
    }

    private onNextPressedListener onNextPressedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);

      groceryText = view.findViewById(R.id.user_groceryDay);

        //groceryText.setText(confirm.mGroceryDay);

       dietaryText = view.findViewById(R.id.user_dietary);

                Button nextButton = view.findViewById(R.id.button4);
        nextButton.setOnClickListener(onNextPressedListener::onNextPressed);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ConfirmViewModel confirmViewModel = ViewModelProviders.of(this).get(ConfirmViewModel.class);

        observeInput(confirmViewModel);
    }

    public void setOnNextPressedListener(onNextPressedListener onNextPressedListener) {
        this.onNextPressedListener = onNextPressedListener;
    }

    private void observeInput(ConfirmViewModel confirmViewModel){
        confirmViewModel.mGroceryDayInp.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                groceryText.setText(s);
            }
        });
    }
}
