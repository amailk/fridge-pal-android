package cp317.wlu.ca.fridgepal.grocerylist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import cp317.wlu.ca.fridgepal.model.GroceryListItem;
import cp317.wlu.ca.fridgepal.repositories.GroceryListRepository;

public class GroceryListViewModel extends ViewModel {
    MutableLiveData<List<GroceryListItem>> groceryListLiveData = new MutableLiveData<>();

    GroceryListRepository groceryListRepository = GroceryListRepository.getInstance();

    GroceryListViewModel() {
        groceryListLiveData.setValue(groceryListRepository.getGroceryList());
        groceryListRepository.addDataLoadedListener(newGroceryList -> groceryListLiveData.setValue(newGroceryList));
    }

    public LiveData<List<GroceryListItem>> getGroceryListLiveData() {
        return groceryListLiveData;
    }

    public void removeGroceryListItem(String id) {
        groceryListRepository.removeGroceryListItem(id);
    }

    public void setChecked(String id, boolean checked) {
        groceryListRepository.setChecked(id, checked);
    }

    public void addToGroceryList(String name) {
        groceryListRepository.addToGroceryList(name);
    }
}
