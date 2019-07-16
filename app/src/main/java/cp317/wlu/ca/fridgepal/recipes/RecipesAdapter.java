package cp317.wlu.ca.fridgepal.recipes;

import android.content.Context;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeTextView;
        public TextView descTextView;
        public ImageView imageDrawable;
        public ImageView imageFavDrawable;

        public ViewHolder(View itemView) {
            super(itemView);

            recipeTextView = (TextView) itemView.findViewById(R.id.name_textview);
            descTextView = (TextView) itemView.findViewById(R.id.desc_textview);
            imageDrawable = (ImageView) itemView.findViewById(R.id.recipe_image);
            imageFavDrawable = (ImageView) itemView.findViewById(R.id.favRecipeItem);
        }
    }

    public interface OnRecipeSelectedListener {
        void onRecipeSelected(Recipe recipe);
    }

    private List<Recipe> recipes;
    private Context context;
    private OnRecipeSelectedListener onRecipeSelectedListener;

    public RecipesAdapter(List<Recipe> recipes, Context context, OnRecipeSelectedListener onRecipeSelectedListener) {
        this.recipes = recipes;
        this.context = context;
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
        textView.setText(recipe.getName());

        TextView descTextView = viewHolder.descTextView;
        descTextView.setText(recipe.getDescription());

        ImageView imageDrawable = viewHolder.imageDrawable;
        imageDrawable.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), recipe.getImage(), null));

        viewHolder.itemView.setOnClickListener(v -> {
            onRecipeSelectedListener.onRecipeSelected(recipes.get(position));
        });

        ImageView fav_img = viewHolder.imageFavDrawable;
        fav_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recipe.getIsFav()==0){
                    fav_img.setImageResource(R.drawable.ic_favorite_black_24dp);
                    recipe.setIsFav(1);
                }
                else{
                    fav_img.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    recipe.setIsFav(0);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

}