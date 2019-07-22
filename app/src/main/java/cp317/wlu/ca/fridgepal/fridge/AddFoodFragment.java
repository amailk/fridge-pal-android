package cp317.wlu.ca.fridgepal.fridge;

import android.app.DatePickerDialog;
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

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Food;
import cp317.wlu.ca.fridgepal.repositories.FoodRepository;

public class AddFoodFragment extends Fragment {
    private TextInputEditText mFoodName;
    private Spinner mFoodCategory;
    private Button selectExpiryButton;

    private Button mAddButton;


    private String foodName = "";
    private String foodCategory;


    private int mDay, mMonth, mYear;
    private Date expiryDate = new Date();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_food_fragment_layout, container, false);

        mFoodName = v.findViewById(R.id.added_food_name);
        mFoodCategory = v.findViewById(R.id.added_food_category);
        selectExpiryButton = v.findViewById(R.id.date_picker_open);
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

        selectExpiryButton.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    (view1, year, month, day) -> {
                        expiryDate = new Date(year, month, day);
                        selectExpiryButton.setText("Expiry: " + Food.DATE_FORMAT.format(expiryDate));
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        });

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