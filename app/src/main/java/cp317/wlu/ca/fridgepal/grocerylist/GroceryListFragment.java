package cp317.wlu.ca.fridgepal.grocerylist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cp317.wlu.ca.fridgepal.R;

public class GroceryListFragment extends Fragment {

    private GroceryListViewModel mViewModel;

    public static GroceryListFragment newInstance() {
        return new GroceryListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.grocery_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(GroceryListViewModel.class);
        // TODO: Use the ViewModel
    }

}
