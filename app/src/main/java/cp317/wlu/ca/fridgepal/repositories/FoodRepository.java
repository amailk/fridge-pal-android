package cp317.wlu.ca.fridgepal.repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cp317.wlu.ca.fridgepal.model.Category;
import cp317.wlu.ca.fridgepal.model.Food;

public class FoodRepository {
    private static final String TAG = "FoodRepository";
    private static FoodRepository instance;
    private static UserRepository userRepository = UserRepository.getInstance();

    private Map<String, Category> fridge;
    private DatabaseReference databaseReference;
    private DataLoadedListener dataLoadedListener;

    public interface DataLoadedListener {
        void onDataLoaded();
    }

    public static FoodRepository getInstance() {
        if (instance == null) {
            instance = new FoodRepository();
        }
        return instance;
    }

    private FoodRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("food").child(userRepository.getUser().getUid());

        fridge = new HashMap<>();
        fridge.put("Meat", new Category("Meat"));
        fridge.put("Dairy", new Category("Dairy"));
        fridge.put("Fruit", new Category("Fruit"));
        fridge.put("Vegetable", new Category("Vegetable"));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "Data loaded: " + dataSnapshot.getChildrenCount());

                for (String key : fridge.keySet()) {
                    fridge.get(key).clear();
                }

                for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {
                    Food food = foodSnapshot.getValue(Food.class);
                    fridge.get(food.getCategory()).addFood(food);
                }

                FoodRepository.this.dataLoadedListener.onDataLoaded();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public ArrayList<Category> getCategories() {
        return new ArrayList<>(fridge.values());
    }

    public void addFood(Food food) {
        databaseReference.push().setValue(food);
    }

    public void setDataLoadedListener(DataLoadedListener dataLoadedListener) {
        this.dataLoadedListener = dataLoadedListener;
    }

}