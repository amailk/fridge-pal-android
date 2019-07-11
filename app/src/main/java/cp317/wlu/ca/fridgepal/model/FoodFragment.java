package cp317.wlu.ca.fridgepal.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cp317.wlu.ca.fridgepal.R;

public class FoodFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.food_fragment_layout, container, false);
        return v;
    }
}