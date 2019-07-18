package cp317.wlu.ca.fridgepal.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import cp317.wlu.ca.fridgepal.R;

public class FoodFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.food_fragment_layout, container, false);

        Food foodName = (Food) getArguments().getSerializable("arg_food_name");

        TextView foodNameText = (TextView) v.findViewById(R.id.food_name_text);
        foodNameText.setText(foodName.getName());
        TextView foodCatText = (TextView) v.findViewById(R.id.food_category_text);
        foodCatText.setText(foodName.getCategory());
        TextView addDate = (TextView) v.findViewById(R.id.date_added_text);
        addDate.setText(foodName.getAddedDate());
        TextView expiryDate = (TextView) v.findViewById(R.id.expiry_date_text);
        expiryDate.setText(foodName.getExpiryDate());
        CheckBox isFav = (CheckBox) v.findViewById(R.id.is_favorite_checkbox);

        isFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                foodName.setIsFav(isChecked);
            }
        });

        return v;
    }

    public static FoodFragment newInstance(Food foodObj)
    {
        Bundle args = new Bundle();
        args.putSerializable("arg_food_name", foodObj);

        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }
}