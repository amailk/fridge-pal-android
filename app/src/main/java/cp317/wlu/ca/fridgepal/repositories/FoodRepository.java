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
import java.util.List;
import java.util.Map;

import cp317.wlu.ca.fridgepal.model.Category;
import cp317.wlu.ca.fridgepal.model.Food;


public class FoodRepository {
    private static final String TAG = "FoodRepository";
    private static final int FRIDGE_CAPACITY = 100;
    private static FoodRepository instance;
    private static UserRepository userRepository;
    protected Map<String, Category> fridge;
    private DatabaseReference databaseReference;
    protected List<DataLoadedListener> dataLoadedListeners = new ArrayList<>();

    private FoodRepository() {
        this.userRepository = UserRepository.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("food").child(userRepository.getUser().getUid());
        init();
    }

    private void init() {
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
                    food.setUuid(foodSnapshot.getKey());
                    fridge.get(food.getCategory()).addFood(food);
                }

                FoodRepository.this.dataLoadedListeners.forEach(x -> x.onDataLoaded());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    FoodRepository(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
        init();
    }

    public static FoodRepository getInstance() {
        if (instance == null) {
            instance = new FoodRepository();
        }
        return instance;
    }

    public ArrayList<Category> getCategories() {
        return new ArrayList<>(fridge.values());
    }

    public boolean addFood(Food food) {
        if (getFridgeSize() < FRIDGE_CAPACITY) {
            databaseReference.push().setValue(food);
            return true;
        }
        return false;
    }

    public void removeFood(Food food) {
        databaseReference.child(food.getUuid()).removeValue();
    }

    public int getFridgeSize() {
        return fridge.values().stream()
                .map(category -> category.getFoods())
                .mapToInt(List::size)
                .sum();

    }

    public void addDataLoadedListener(DataLoadedListener dataLoadedListener) {
        this.dataLoadedListeners.add(dataLoadedListener);
    }

    public interface DataLoadedListener {
        void onDataLoaded();
    }
}