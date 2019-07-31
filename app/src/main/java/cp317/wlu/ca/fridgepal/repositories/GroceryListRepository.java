package cp317.wlu.ca.fridgepal.repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cp317.wlu.ca.fridgepal.model.GroceryListItem;

public class GroceryListRepository {
    private static final String TAG = GroceryListRepository.class.getSimpleName();

    private static UserRepository userRepository = UserRepository.getInstance();
    private static GroceryListRepository instance;
    private final DatabaseReference databaseReference;
    private List<DataLoadedListener> dataLoadedListeners = new ArrayList<>();
    private List<GroceryListItem> groceryList = Collections.emptyList();


    private GroceryListRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("groceryList").child(userRepository.getUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "Data loaded: " + dataSnapshot.getChildrenCount());

                List<GroceryListItem> groceryList = new ArrayList<>();
                for (DataSnapshot groceryListItemSnapshot : dataSnapshot.getChildren()) {
                    GroceryListItem item = groceryListItemSnapshot.getValue(GroceryListItem.class);
                    item.setId(groceryListItemSnapshot.getKey());
                    groceryList.add(item);
                }

                GroceryListRepository.this.groceryList = groceryList;
                GroceryListRepository.this.dataLoadedListeners.forEach(x -> x.onDataLoaded(groceryList));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled" + databaseError.getMessage());
            }
        });
    }

    public static GroceryListRepository getInstance() {
        if (instance == null) {
            instance = new GroceryListRepository();
        }
        return instance;
    }

    public void addDataLoadedListener(DataLoadedListener dataLoadedListener) {
        this.dataLoadedListeners.add(dataLoadedListener);
    }

    public List<GroceryListItem> getGroceryList() {
        return groceryList;
    }

    public void removeGroceryListItem(String id) {
        databaseReference.child(id).removeValue();
    }

    public void setChecked(String id, boolean checked) {
        Log.d(TAG, "Setting checked for " + id + " " + checked);
        databaseReference.child(id).child("checked").setValue(checked);
    }

    public void addToGroceryList(String name) {
        GroceryListItem groceryListItem = new GroceryListItem();
        groceryListItem.setChecked(false);
        groceryListItem.setName(name);

        databaseReference.push().setValue(groceryListItem);
    }

    public interface DataLoadedListener {
        void onDataLoaded(List<GroceryListItem> groceryList);
    }
}
