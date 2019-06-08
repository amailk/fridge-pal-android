package cp317.wlu.ca.fridgepal;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class LoginActivity extends Activity implements gd_fragment.GDay {


    @Override
    public void gdaysearch(String day) {
        //use this to communicate to the next button
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void onLoginClicked(View view) {
        Intent intent = new Intent(this, SignupFlowActivity.class);
        startActivity(intent);
    }
}
