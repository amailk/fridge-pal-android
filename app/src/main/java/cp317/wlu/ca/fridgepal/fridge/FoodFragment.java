package cp317.wlu.ca.fridgepal.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Food;
import cp317.wlu.ca.fridgepal.repositories.FoodRepository;
import cp317.wlu.ca.fridgepal.repositories.SpoonacularRepository;

public class FoodFragment extends Fragment {

    private SpoonacularRepository spoonacularRepository;

    public static FoodFragment newInstance(Food foodObj) {
        Bundle args = new Bundle();
        args.putSerializable("arg_food_name", foodObj);

        FoodFragment fragment = new FoodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spoonacularRepository = new SpoonacularRepository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment_layout, container, false);

        Food food = (Food) getArguments().getSerializable("arg_food_name");

        TextView foodNameText = view.findViewById(R.id.textview_food_name);
        foodNameText.setText(food.getName());

        TextView expiryDate = view.findViewById(R.id.textview_expiry_date);
        expiryDate.setText(food.getExpiryDate());

        view.findViewById(R.id.fab_delete).setOnClickListener(v -> {
            FoodRepository.getInstance().removeFood(food);
            getActivity().finish();
        });

        view.findViewById(R.id.button_suggested_recipes).setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SuggestedRecipesActivity.class);
            intent.putExtra(SuggestedRecipesActivity.EXTRA_INGREDIENT_NAME, food.getName());
            startActivity(intent);
        });

        spoonacularRepository.fetchFoodInformation(food.getName(), foodWithData -> {
            ImageView foodImageView = view.findViewById(R.id.image_view_food);
            Picasso.get().load(foodWithData.getImage()).fit().centerInside().into(foodImageView);
        });

        spoonacularRepository.fetchNutritionInfo(food.getName(), nutritionInfo -> {
            TextView caloriesText = view.findViewById(R.id.calories);
            TextView proteinText = view.findViewById(R.id.protein);
            TextView fatText = view.findViewById(R.id.fat);
            TextView carbsText = view.findViewById(R.id.carbs);

            caloriesText.setText(nutritionInfo.getCalories().getValue() + " calories");
            proteinText.setText(nutritionInfo.getProtein().getValue() + "g");
            fatText.setText(nutritionInfo.getFat().getValue() + "g");
            carbsText.setText(nutritionInfo.getCarbs().getValue() + "g");
        });

        return view;
    }
}