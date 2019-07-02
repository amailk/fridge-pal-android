package cp317.wlu.ca.fridgepal.signupflow;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroceryDayFragment extends Fragment {

    interface OnNextPressedListener {
        void onNextPressed(View view);
    }

    private OnNextPressedListener onNextPressedListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grocery_day, container, false);
        Spinner spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button nextButton = view.findViewById(R.id.button_next);
        nextButton.setOnClickListener(onNextPressedListener::onNextPressed);
        return view;
    }

    public void setOnNextPressedListener(OnNextPressedListener onNextPressedListener) {
        this.onNextPressedListener = onNextPressedListener;
    }
}
