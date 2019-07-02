package cp317.wlu.ca.fridgepal;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cp317.wlu.ca.fridgepal.model.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTextView;
        public TextView descTextView;
        public ImageView imageDrawable;

        public ViewHolder(View itemView) {
            super(itemView);

            recipeTextView = (TextView) itemView.findViewById(R.id.name_textview);
            descTextView = (TextView) itemView.findViewById(R.id.desc_textview);
            imageDrawable = (ImageView) itemView.findViewById(R.id.recipe_image);

        }
    }

    private List<Recipe> recipes;
    private Context context;

    public RecipesAdapter(List<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this.context = context;

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
        textView.setText(recipe.getName());

        TextView descTextView = viewHolder.descTextView;
        descTextView.setText(recipe.getDescription());

        ImageView imageDrawable = viewHolder.imageDrawable;
        imageDrawable.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), recipe.getImage(), null));

        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeActivity.class);
            intent.putExtra(RecipeActivity.EXTRA_RECIPE, recipes.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

}