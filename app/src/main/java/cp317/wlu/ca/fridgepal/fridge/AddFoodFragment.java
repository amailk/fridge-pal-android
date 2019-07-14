package cp317.wlu.ca.fridgepal.fridge;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Date;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Food;
import cp317.wlu.ca.fridgepal.repositories.FoodRepository;

public class AddFoodFragment extends Fragment {
    private EditText mFoodName;
    private Spinner mFoodCategory;
    private DatePicker mExpiryDate;
    private Button mAddButton;

    private String foodName;
    private String foodCategory;

    private Date expiryDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_food_fragment_layout, container, false);

        mFoodName = v.findViewById(R.id.added_food_name);
        mFoodCategory = v.findViewById(R.id.added_food_category);
        mExpiryDate = v.findViewById(R.id.added_food_expiration_date);
        mAddButton = v.findViewById(R.id.added_food_add_button);

        mFoodName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                foodName = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mFoodCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                foodCategory = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        int day, month, year;

        day = mExpiryDate.getDayOfMonth();
        month = mExpiryDate.getMonth();
        year = mExpiryDate.getYear();

        Calendar c2 = Calendar.getInstance();
        c2.set(year, month, day);
        expiryDate = c2.getTime();

        mAddButton.setOnClickListener(view -> {
            Food food = new Food(foodName, foodCategory, expiryDate.toString());

            FoodRepository.getInstance().addFood(food);
            getActivity().finish();
        });

        return v;
    }
}