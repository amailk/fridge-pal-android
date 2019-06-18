package cp317.wlu.ca.fridgepal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class LoginActivity extends AppCompatActivity {


    //public class LoginActivity extends Activity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
        }

        public void onLoginClicked(View view) {
            Intent intent = new Intent(this, SignupFlowActivity.class);
            startActivity(intent);
        }
    //}
}
