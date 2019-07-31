package cp317.wlu.ca.fridgepal.settings;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;

import cp317.wlu.ca.fridgepal.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment {

    private static final String PREF_GROCERY_DAYS = "pref_grocery_day";
    private static final String PREF_DIET = "pref_diet";
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.setting_preference);
        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (key.equals(PREF_GROCERY_DAYS)) {
                    Preference groceryPref = findPreference(key);
                    groceryPref.setSummary(sharedPreferences.getString(key, ""));
                }

                if (key.equals(PREF_DIET)) {
                    Preference dietPref = findPreference(key);
                    dietPref.setSummary(sharedPreferences.getString(key, ""));
                }
            }
        };

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);

        Preference groceryPref = findPreference(PREF_GROCERY_DAYS);
        groceryPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(PREF_GROCERY_DAYS, ""));

        Preference dietPref = findPreference(PREF_DIET);
        dietPref.setSummary(getPreferenceScreen().getSharedPreferences().getString(PREF_DIET, ""));
    }

    @Override
    public void onPause() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }

}
