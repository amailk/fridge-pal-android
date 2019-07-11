package cp317.wlu.ca.fridgepal.signupflow;

import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import cp317.wlu.ca.fridgepal.MainActivity;
import cp317.wlu.ca.fridgepal.R;


public class SignupFlowActivity extends AppCompatActivity {

    private GroceryDayFragment groceryDayFragment;
    private DietaryPreferenceFragment dietaryPreferenceFragment;
    private ConfirmFragment confirmFragment;
    private ConfirmViewModel mConfirmViewModel;



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

        mConfirmViewModel = ViewModelProviders.of(this).get(ConfirmViewModel.class);
        mConfirmViewModel.init();

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