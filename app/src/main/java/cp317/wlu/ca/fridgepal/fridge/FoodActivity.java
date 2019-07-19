package cp317.wlu.ca.fridgepal.fridge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Food;

public class FoodActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_fragment_container);

        FragmentManager fm = getSupportFragmentManager();

        Food foodName = (Food) getIntent().getSerializableExtra("args_food_id");

        Fragment f = fm.findFragmentById(R.id.food_fragment_container);
        if(f == null)
        {
            f = FoodFragment.newInstance(foodName);
            fm.beginTransaction().add(R.id.food_fragment_container, f).commit();
        }
    }

    public static Intent newIntent(Context packageContext, Food foodObj) // String param to be changed to food id uuid.
    {
        Intent intent = new Intent(packageContext, FoodActivity.class);
        intent.putExtra("args_food_id", foodObj);
        return intent;
    }
}