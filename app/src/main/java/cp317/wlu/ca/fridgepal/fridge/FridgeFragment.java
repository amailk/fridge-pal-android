package cp317.wlu.ca.fridgepal.fridge;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import cp317.wlu.ca.fridgepal.R;

public class FridgeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fridge_fragment_layout, container, false);

        Button viewContentsButton = v.findViewById(R.id.view_contents_button);
        viewContentsButton.setOnClickListener(v1 -> {
            Intent intent = new Intent(getActivity(), FridgeListActivity.class);
            startActivity(intent);
        });

        Button addFoodButton = v.findViewById(R.id.add_food_item_button);
        addFoodButton.setOnClickListener(v12 -> {
            Intent intent = new Intent(getActivity(), AddFoodActivity.class);
            startActivity(intent);
        });

        return v;
    }
}