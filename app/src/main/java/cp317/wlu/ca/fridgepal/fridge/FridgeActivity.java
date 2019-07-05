package cp317.wlu.ca.fridgepal.fridge;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FridgeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fridge_fragment_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fridge_fragment_container);
        if(f == null)
        {
            f = new FridgeFragment();
            fm.beginTransaction().add(R.id.fridge_fragment_container, f).commit();
        }
    }
}