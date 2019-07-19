package cp317.wlu.ca.fridgepal.fridge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Food;
import cp317.wlu.ca.fridgepal.repositories.FoodRepository;

public class FoodFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        TextView isPriority = (TextView) v.findViewById(R.id.priority_item);

        Button deleteFoodButton = (Button) v.findViewById(R.id.delete_food_button);

        deleteFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodRepository.getInstance().removedFood(foodName);
                getActivity().finish();
            }
        });

        return v;
    }

    public static FoodFragment newInstance(Food foodObj) {
        Bundle args = new Bundle();
        args.putSerializable("arg_food_name", foodObj);

        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }
}