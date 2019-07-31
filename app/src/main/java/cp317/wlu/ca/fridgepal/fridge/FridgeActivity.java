package cp317.wlu.ca.fridgepal.fridge;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import cp317.wlu.ca.fridgepal.R;

public class FridgeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fridge_fragment_container);

        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fridge_fragment_container);
        if (f == null) {
            f = new FridgeFragment();
            fm.beginTransaction().add(R.id.fridge_fragment_container, f).commit();
        }
    }
}