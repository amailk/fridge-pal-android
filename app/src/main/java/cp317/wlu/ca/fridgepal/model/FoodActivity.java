package cp317.wlu.ca.fridgepal.model;

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
        Fragment f = fm.findFragmentById(R.id.food_fragment_container);
        if(f == null)
        {
            f = new FoodFragment();
            fm.beginTransaction().add(R.id.food_fragment_container, f).commit();
        }
    }
}