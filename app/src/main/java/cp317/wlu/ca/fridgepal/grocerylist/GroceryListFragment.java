package cp317.wlu.ca.fridgepal.grocerylist;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.recipes.RecipesAdapter;
import cp317.wlu.ca.fridgepal.recipes.RecipesFragment;
import cp317.wlu.ca.fridgepal.recipes.RecipesViewModel;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = new Intent(getActivity(), GroceryListActicity.class);
        startActivity(intent);
    }


}
