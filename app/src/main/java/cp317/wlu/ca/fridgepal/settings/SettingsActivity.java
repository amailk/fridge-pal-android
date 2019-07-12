package cp317.wlu.ca.fridgepal.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cp317.wlu.ca.fridgepal.R;

import static cp317.wlu.ca.fridgepal.R.id.setting_fragment_container;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        if (findViewById(setting_fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            getFragmentManager().beginTransaction().add(R.id.setting_fragment_container, new SettingsFragment()).commit();
        }
    }
}
