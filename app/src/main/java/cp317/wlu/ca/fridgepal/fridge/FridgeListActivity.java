package cp317.wlu.ca.fridgepal.fridge;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import cp317.wlu.ca.fridgepal.R;

public class FridgeListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fridge_list_fragment_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fridge_list_fragment_container);
        if (f == null) {
            f = new FridgeListFragment();
            fm.beginTransaction().add(R.id.fridge_list_fragment_container, f).commit();
        }
    }
}