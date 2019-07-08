package cp317.wlu.ca.fridgepal.signupflow;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

       // mConfirm.mGroceryDay = groceryDayFragment.text;

        groceryDayFragment.setOnNextPressedListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, dietaryPreferenceFragment)
                .commit());

       // mConfirm = new ConfirmViewModel("", "");
        //setGroceryDayText


        dietaryPreferenceFragment.setOnNextPressedListener(v -> getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, confirmFragment)
                .commit());

       // System.out.printf("%s" , mConfirm.getmGroceryDay());

        confirmFragment.setOnNextPressedListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


    }
}