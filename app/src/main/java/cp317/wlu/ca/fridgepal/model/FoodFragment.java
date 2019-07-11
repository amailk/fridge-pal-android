package cp317.wlu.ca.fridgepal.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import cp317.wlu.ca.fridgepal.R;

public class FoodFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.food_fragment_layout, container, false);

        String foodName = (String) getArguments().getString("arg_food_name");
        TextView foodNameText = (TextView) v.findViewById(R.id.food_name_text);
        foodNameText.setText(foodName);

        return v;
    }

    public static FoodFragment newInstance(String foodName)
    {
        Bundle args = new Bundle();
        args.putString("arg_food_name", foodName);

        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }
}