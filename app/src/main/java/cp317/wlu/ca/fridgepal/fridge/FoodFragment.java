package cp317.wlu.ca.fridgepal.fridge;

import android.content.Intent;
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
        View view = inflater.inflate(R.layout.food_fragment_layout, container, false);

        Food foodName = (Food) getArguments().getSerializable("arg_food_name");

        TextView foodNameText = view.findViewById(R.id.food_name_text);
        foodNameText.setText(foodName.getName());
        TextView foodCatText = view.findViewById(R.id.food_category_text);
        foodCatText.setText(foodName.getCategory());
        TextView addDate = view.findViewById(R.id.date_added_text);
        addDate.setText(foodName.getAddedDate());
        TextView expiryDate = view.findViewById(R.id.expiry_date_text);
        expiryDate.setText(foodName.getExpiryDate());
        TextView isPriority = view.findViewById(R.id.priority_item);

        Button deleteFoodButton = view.findViewById(R.id.delete_food_button);

        deleteFoodButton.setOnClickListener(v -> {
            FoodRepository.getInstance().removedFood(foodName);
            getActivity().finish();
        });

        view.findViewById(R.id.button_suggested_recipes).setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SuggestedRecipesActivity.class);
            intent.putExtra(SuggestedRecipesActivity.EXTRA_INGREDIENT_NAME, foodName.getName());
            startActivity(intent);
        });

        return view;
    }

    public static FoodFragment newInstance(Food foodObj) {
        Bundle args = new Bundle();
        args.putSerializable("arg_food_name", foodObj);

        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }
}