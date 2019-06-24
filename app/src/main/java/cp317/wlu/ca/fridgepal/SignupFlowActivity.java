package cp317.wlu.ca.fridgepal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class SignupFlowActivity extends AppCompatActivity {

    private GroceryDayFragment groceryDayFragment;
    private DietaryPreferenceFragment dietaryPreferenceFragment;
    private ConfirmFragment confirmFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_flow);

        setupFragments();

        //start activity with grocery day fragment.
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, groceryDayFragment)
                .commit();
    }

    private void setupFragments() {
        groceryDayFragment = new GroceryDayFragment();
        dietaryPreferenceFragment = new DietaryPreferenceFragment();
        confirmFragment = new ConfirmFragment();

        groceryDayFragment.setOnNextPressedListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, dietaryPreferenceFragment)
                .commit());

        dietaryPreferenceFragment.setOnNextPressedListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, confirmFragment)
                .commit());

        confirmFragment.setOnNextPressedListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}