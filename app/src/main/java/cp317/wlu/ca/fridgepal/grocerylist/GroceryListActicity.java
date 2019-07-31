package cp317.wlu.ca.fridgepal.grocerylist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class GroceryListActicity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView sv = new ScrollView(this);
        final LinearLayout label = new LinearLayout(this);
        label.setOrientation(LinearLayout.VERTICAL);
        sv.addView(label);

        final LinearLayout ll2 = new LinearLayout(this);
        ll2.setOrientation(LinearLayout.VERTICAL);
        label.addView(ll2);
        final ArrayList<CheckBox> array = new ArrayList<>();

        TextView tv = new TextView(this);
        label.addView(tv);

        final EditText test = new EditText(this);
        //label.addView(test);
        final EditText input = new EditText(this);
        label.addView(input);
        Button b = new Button(this);
        b.setText("add");
        label.addView(b);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final CheckBox cb = new CheckBox(getApplicationContext());
                cb.setText(input.getText());
                array.add(cb);
                input.setText("");
                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                      ll2.removeView(cb);
                                                  }
                                              }
                );
                ll2.addView(cb);
            }
        });

        this.setContentView(sv);
    }
}

