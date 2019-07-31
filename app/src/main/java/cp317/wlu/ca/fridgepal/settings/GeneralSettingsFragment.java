package cp317.wlu.ca.fridgepal.settings;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import cp317.wlu.ca.fridgepal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralSettingsFragment extends Fragment {


    public GeneralSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_settings, container, false);
    }

}
