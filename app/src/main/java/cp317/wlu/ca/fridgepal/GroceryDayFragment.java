package cp317.wlu.ca.fridgepal;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroceryDayFragment extends Fragment {


   /* private DayViewModel viewModel;

   @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this.getActivity()).get(DayViewModel.class);

        viewModel.getSelectedDay().observe(this, item -> {
            displayDetails(viewModel.getSelectedDayDetails(item));
        });
    }

    public void displayDetails(Day day){

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_grocery_day, container, false);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.days, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item, );

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity() ,android.R.layout.simple_spinner_dropdown_item,  R.array.days);


        Button nextButton = (Button)view.findViewById(R.id.button3);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                swapfragment();
            }
        });
        return view;
        // TextView output = (TextView)view.findViewById(R.id.)
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_grocery_day, container, false);
    }

    private void swapfragment(){
        DietaryPreferenceFragment newfragment = new DietaryPreferenceFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl1, newfragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

}
