package cp317.wlu.ca.fridgepal.preferences;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cp317.wlu.ca.fridgepal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DietaryPreferenceFragment extends Fragment {

    interface OnNextPressedListener {
        void onNextPressed(View view);
    }

    private OnNextPressedListener onNextPressedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dietary_preference, container, false);

        Button nextButton = view.findViewById(R.id.button_nextC);
        nextButton.setOnClickListener(onNextPressedListener::onNextPressed);
        return view;
    }

    public void setOnNextPressedListener(OnNextPressedListener onNextPressedListener) {
        this.onNextPressedListener = onNextPressedListener;
    }
}
