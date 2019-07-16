package cp317.wlu.ca.fridgepal.preferences;

import android.os.Bundle;

import cp317.wlu.ca.fridgepal.R;

public class MyPreferencesFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}