package cp317.wlu.ca.fridgepal.grocerylist;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.GroceryListItem;

public class GroceryListFragment extends Fragment {
    private static final String TAG = GroceryListFragment.class.getSimpleName();

    private GroceryListViewModel viewModel;

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
        viewModel = ViewModelProviders.of(this).get(GroceryListViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        LiveData<List<GroceryListItem>> groceryListLiveDta = viewModel.getGroceryListLiveData();

        groceryListLiveDta.observe(this, groceryList -> {
            Log.d(TAG, "GroceryList: " + groceryList.size());

            GroceryItemAdapter adapter = new GroceryItemAdapter(
                    groceryList,
                    groceryItemId -> viewModel.removeGroceryListItem(groceryItemId),
                    (id, checked) -> viewModel.setChecked(id, checked));

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        });

        FloatingActionButton addGroceryItemButton = view.findViewById(R.id.add_grocery_item_button);
        addGroceryItemButton.setOnClickListener(v -> {
            new MaterialDialog.Builder(getContext())
                    .title("Name of the grocery item")
                    .inputRangeRes(2, 20, R.color.colorError)
                    .input(null, null, (dialog, input) -> viewModel.addToGroceryList(input.toString())).show();
        });
    }

    public interface OnRemoveGroceryItemListener {
        void onGroceryItemRemoved(String groceryItemId);
    }

    public interface OnGroceryListItemCheckedListener {
        void onGroceryListItemChecked(String id, boolean checked);
    }

    public class GroceryItemAdapter extends RecyclerView.Adapter<GroceryItemAdapter.ViewHolder> {

        private final OnRemoveGroceryItemListener removeListener;
        private final OnGroceryListItemCheckedListener checkedListener;
        private List<GroceryListItem> groceryList;

        public GroceryItemAdapter(List<GroceryListItem> groceryList, OnRemoveGroceryItemListener removeListener, OnGroceryListItemCheckedListener checkedListener) {
            this.groceryList = groceryList;
            this.removeListener = removeListener;
            this.checkedListener = checkedListener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View groceryListItemView = inflater.inflate(R.layout.item_grocery_list_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(groceryListItemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            GroceryListItem item = groceryList.get(position);

            holder.checkBox.setChecked(item.isChecked());
            holder.checkBox.setText(item.getName());
            holder.checkBox.setOnCheckedChangeListener((compoundButton, checked) -> this.checkedListener.onGroceryListItemChecked(item.getId(), checked));

            holder.remove.setOnClickListener(v -> this.removeListener.onGroceryItemRemoved(item.getId()));
        }

        @Override
        public int getItemCount() {
            return groceryList.size();
        }

        public void setGroceryList(List<GroceryListItem> groceryList) {
            this.groceryList = groceryList;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            CheckBox checkBox;
            ImageButton remove;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                checkBox = itemView.findViewById(R.id.checkBox);
                remove = itemView.findViewById(R.id.button_remove);
            }
        }
    }
}
