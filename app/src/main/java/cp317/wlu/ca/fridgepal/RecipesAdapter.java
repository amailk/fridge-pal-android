package cp317.wlu.ca.fridgepal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cp317.wlu.ca.fridgepal.model.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            recipeTextView = (TextView) itemView.findViewById(R.id.name_textview);
        }
    }

    private List<Recipe> mRecipes;

    public RecipesAdapter(List<Recipe> recipes) {
        mRecipes = recipes;
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
        Recipe recipe = mRecipes.get(position);
        TextView textView = viewHolder.recipeTextView;
        textView.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

}