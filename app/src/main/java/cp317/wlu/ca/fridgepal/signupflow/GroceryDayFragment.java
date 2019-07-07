package cp317.wlu.ca.fridgepal.signupflow;
import cp317.wlu.ca.fridgepal.R;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    private  ConfirmViewModel confirmViewModel;
    String text;

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
        text = spinner.getSelectedItem().toString();

        Button nextButton = view.findViewById(R.id.button_next);
        nextButton.setOnClickListener(onNextPressedListener::onNextPressed);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        confirmViewModel = ViewModelProviders.of(this).get(ConfirmViewModel.class);
        String grocerytext = "";
        if(!text.isEmpty()){
            grocerytext = text;
        }
        confirmViewModel.mGroceryDayInp.postValue(grocerytext);
    }

    public void setOnNextPressedListener(OnNextPressedListener onNextPressedListener) {


        this.onNextPressedListener = onNextPressedListener;
    }


}
