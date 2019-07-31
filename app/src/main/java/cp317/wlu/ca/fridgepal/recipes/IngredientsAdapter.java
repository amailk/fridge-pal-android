package cp317.wlu.ca.fridgepal.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Food;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private List<Food> ingredients;

    public IngredientsAdapter(List<Food> ingredients) {
        System.out.println("Ingredients size: " + ingredients.size());
        this.ingredients = ingredients;
    }

    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View ingredientView = inflater.inflate(R.layout.item_ingredient, parent, false);

        ViewHolder viewHolder = new ViewHolder(ingredientView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientsAdapter.ViewHolder viewHolder, int position) {
        Food ingredient = ingredients.get(position);

        viewHolder.name.setText(ingredient.getName());
        viewHolder.amount.setText(ingredient.getAmount() + " " + ingredient.getUnit());
        Picasso.get().load(ingredient.getImage()).fit().centerInside().into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView amount;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.number);
            image = itemView.findViewById(R.id.image);
            amount = itemView.findViewById(R.id.step);
        }
    }

}
