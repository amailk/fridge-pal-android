package cp317.wlu.ca.fridgepal.fridge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import cp317.wlu.ca.fridgepal.R;

public class FridgeListActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fridge_list_fragment_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fridge_list_fragment_container);
        if(f == null)
        {
            f = new FridgeListFragment();
            fm.beginTransaction().add(R.id.fridge_list_fragment_container, f).commit();
        }
    }
}