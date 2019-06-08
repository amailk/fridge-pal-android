package cp317.wlu.ca.fridgepal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class DietaryPreferenceFragment extends Fragment {


    public DietaryPreferenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dietary_preference, container, false);

        Button nextButton = (Button)view.findViewById(R.id.button_nextC);
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
        ConfirmFragment newfragment = new ConfirmFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl1, newfragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

}
