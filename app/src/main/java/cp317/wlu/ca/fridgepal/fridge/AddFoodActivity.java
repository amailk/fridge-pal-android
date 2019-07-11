package cp317.wlu.ca.fridgepal.fridge;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import cp317.wlu.ca.fridgepal.R;

public class AddFoodActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food_fragment_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.add_food_fragment_container);
        if(f == null)
        {
            f = new AddFoodFragment();
            fm.beginTransaction().add(R.id.add_food_fragment_container, f).commit();
        }
    }
}