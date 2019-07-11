package cp317.wlu.ca.fridgepal.model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import cp317.wlu.ca.fridgepal.R;

public class FoodActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_fragment_container);

        FragmentManager fm = getSupportFragmentManager();

        String foodName = getIntent().getStringExtra("args_food_id");

        Fragment f = fm.findFragmentById(R.id.food_fragment_container);
        if(f == null)
        {
            f = FoodFragment.newInstance(foodName);
            fm.beginTransaction().add(R.id.food_fragment_container, f).commit();
        }
    }

    public static Intent newIntent(Context packageContext, String foodName) // String param to be changed to food id uuid.
    {
        Intent intent = new Intent(packageContext, FoodActivity.class);
        intent.putExtra("args_food_id", foodName);
        return intent;
    }
}