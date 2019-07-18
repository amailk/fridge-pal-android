package cp317.wlu.ca.fridgepal.fridge;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Category;
import cp317.wlu.ca.fridgepal.model.Food;
import cp317.wlu.ca.fridgepal.model.FoodActivity;
import cp317.wlu.ca.fridgepal.repositories.FoodRepository;

public class FridgeListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private FoodRepository foodRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foodRepository = FoodRepository.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fridge_list_fragment_layout, container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new ItemAdapter(foodRepository.getCategories());
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton addButon = view.findViewById(R.id.add_food_item_button);
        addButon.setOnClickListener( v -> {
            Intent intent = new Intent(getActivity(), AddFoodActivity.class);
            startActivity(intent);
        });

        foodRepository.addDataLoadedListener(() -> {
            mAdapter.setCategories(foodRepository.getCategories());
        });

        return view;
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;
        private RecyclerView foodRecyclerView;

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fridge_list_item_layout, parent, false));
            categoryName = itemView.findViewById(R.id.category_name);
            foodRecyclerView = itemView.findViewById(R.id.food_recycler_view);
        }
    }

    private class ItemHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView foodName;
        private Food foodObj;

        public ItemHolder2(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fridge_list_item_layout_2, parent, false));
            itemView.setOnClickListener(this);
            foodName = itemView.findViewById(R.id.food_name);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
            Intent intent = FoodActivity.newIntent(getActivity(), foodObj);
            //intent.putExtra("arg_food_name", foodObj.getName());
            startActivity(intent);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private ArrayList<Category> mCategories;

        public ItemAdapter(ArrayList<Category> tempCategories) {
            mCategories = tempCategories;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new ItemHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            String s = mCategories.get(position).getCategoryName();
            holder.categoryName.setText(s);
            ItemAdapter2 adapter2 = new ItemAdapter2(mCategories.get(position).getFoods());
            holder.foodRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            holder.foodRecyclerView.setAdapter(adapter2);
        }

        public void setCategories(ArrayList<Category> categories) {
            this.mCategories = categories;
            this.notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mCategories.size();
        }
    }

    private class ItemAdapter2 extends RecyclerView.Adapter<ItemHolder2> {
        private ArrayList<Food> mFoods;

        public ItemAdapter2(ArrayList<Food> tempFoods) {
            mFoods = tempFoods;
        }

        @Override
        public ItemHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new ItemHolder2(inflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder2 holder, int position) {
            String s = mFoods.get(position).getName();
            holder.foodObj = mFoods.get(position);
            holder.foodName.setText(s);
        }

        @Override
        public int getItemCount() {
            return mFoods.size();
        }
    }
}