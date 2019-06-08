package cp317.wlu.ca.fridgepal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class SignupFlowActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_flow);

        ConfirmFragment confirmFragment = new ConfirmFragment();

        DietaryPreferenceFragment dietaryPreferenceFragment = new DietaryPreferenceFragment();
        dietaryPreferenceFragment.setOnNextPressedListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, confirmFragment)
                .commit());

        GroceryDayFragment groceryDayFragment = new GroceryDayFragment();
        groceryDayFragment.setOnNextPressedListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, dietaryPreferenceFragment)
                .commit());

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, groceryDayFragment)
                .commit();
    }
}

