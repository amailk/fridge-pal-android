package cp317.wlu.ca.fridgepal.recipes;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Ingredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ingNameTextView;
        public TextView ingQuantityTextView;
        public TextView ingAmountTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            ingNameTextView = (TextView) itemView.findViewById(R.id.ingname_text_view);
            ingAmountTextView = (TextView) itemView.findViewById(R.id.ingamount_textview);
            ingQuantityTextView = (TextView) itemView.findViewById(R.id.ingquantity_textview);
        }
    }

    private List<Ingredient> ingredients;
    private Context context;

    public IngredientsAdapter(List<Ingredient> ingredients, Context context) {
        System.out.println("Ingredients size: " + ingredients.size());
        this.ingredients = ingredients;
        this.context = context;
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
        System.out.println("position: " + position);
        Ingredient ingredient = ingredients.get(position);

        TextView ingNameTextView = viewHolder.ingNameTextView;
        ingNameTextView.setText(ingredient.getName());

        TextView ingAmountTextView = viewHolder.ingAmountTextView;
        ingAmountTextView.setText(Integer.toString(ingredient.getAmount()));

        TextView ingQuantityTextView = viewHolder.ingQuantityTextView;
        ingQuantityTextView.setText(ingredient.getQuantityType().toString());

    }

    @Override
    public int getItemCount() {
        System.out.println("Size: " + ingredients.size());
        return ingredients.size();
    }

}
