package cp317.wlu.ca.fridgepal.recipes;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTextView;
        public ImageView imageDrawable;

        public ViewHolder(View itemView) {
            super(itemView);

            recipeTextView = itemView.findViewById(R.id.name_textview);
            imageDrawable = itemView.findViewById(R.id.recipe_image);
        }
    }

    public interface OnRecipeSelectedListener {
        void onRecipeSelected(Recipe recipe);
    }

    private List<Recipe> recipes;
    private OnRecipeSelectedListener onRecipeSelectedListener;

    public RecipesAdapter(List<Recipe> recipes, OnRecipeSelectedListener onRecipeSelectedListener) {
        this.recipes = recipes;
        this.onRecipeSelectedListener = onRecipeSelectedListener;
    }

    @Override
    public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recipeView = inflater.inflate(R.layout.item_recipe, parent, false);

        ViewHolder viewHolder = new ViewHolder(recipeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipesAdapter.ViewHolder viewHolder, int position) {
        Recipe recipe = recipes.get(position);

        TextView textView = viewHolder.recipeTextView;
        textView.setText(recipe.getTitle());

        ImageView imageDrawable = viewHolder.imageDrawable;
        Picasso.get()
                .load(recipe.getImage())
                .fit()
                .centerCrop()
                .into(imageDrawable);

        viewHolder.itemView.setOnClickListener(v -> {
            onRecipeSelectedListener.onRecipeSelected(recipes.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

}