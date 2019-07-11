package cp317.wlu.ca.fridgepal.fridge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import cp317.wlu.ca.fridgepal.R;

public class AddFoodFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.add_food_fragment_layout, container, false);
        return v;
    }
}