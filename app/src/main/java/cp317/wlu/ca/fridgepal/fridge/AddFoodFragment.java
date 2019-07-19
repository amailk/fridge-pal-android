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
import android.widget.Toast;

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
    private Button mBarcodeButton;

    private String foodName = "";
    private String foodCategory;

    int day, month, year02;

    private Date expiryDate = new Date();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_food_fragment_layout, container, false);

        mFoodName = v.findViewById(R.id.added_food_name);
        mFoodCategory = v.findViewById(R.id.added_food_category);
        mExpiryDate = v.findViewById(R.id.added_food_expiration_date);
        mAddButton = v.findViewById(R.id.added_food_add_button);

        mBarcodeButton = v.findViewById(R.id.button_barcode);

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


        mExpiryDate.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                day = dayOfMonth;
                month = monthOfYear;
                year02 = year;

                Calendar c2 = Calendar.getInstance();
                c2.set(year02, month, day);
                expiryDate = c2.getTime();
            }
        });

        //day = mExpiryDate.getDayOfMonth();
        //month = mExpiryDate.getMonth();
        //year = mExpiryDate.getYear();

        //Calendar c2 = Calendar.getInstance();
        //c2.set(year02, month, day);
        //expiryDate = c2.getTime();

        mAddButton.setOnClickListener(view -> {
            Date d = new Date();
            if (foodName.equals("")) {
                Toast.makeText(getActivity(), "You have not entered a food name", Toast.LENGTH_SHORT).show();
            } else if (d.compareTo(expiryDate) > 0 || d.compareTo(expiryDate) == 0) {
                Toast.makeText(getActivity(), "Date of expiry cannot be before tomorrow's date", Toast.LENGTH_SHORT).show();
            } else {
                Food food = new Food(foodName, foodCategory, expiryDate);

                FoodRepository.getInstance().addFood(food);
                getActivity().finish();
            }
        });

        return v;
    }
}