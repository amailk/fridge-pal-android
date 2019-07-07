package cp317.wlu.ca.fridgepal.recipes;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.stream.Collectors;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Ingredient;
import cp317.wlu.ca.fridgepal.model.Recipe;

public class RecipeActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "extra_recipe";
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipe = getIntent().getExtras().getParcelable(EXTRA_RECIPE);

        TextView title = findViewById(R.id.title);
        title.setText(recipe.getName());

        ImageView image = findViewById(R.id.image);
        image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), recipe.getImage(), null));

        TextView description = findViewById(R.id.description);
        description.setText(recipe.getDescription());

        final RecyclerView ingredientsRecyclerView = findViewById(R.id.ingredient_recycler_view);

        IngredientsAdapter adapter = new IngredientsAdapter(recipe.getIngredients(), this);
        ingredientsRecyclerView.setAdapter(adapter);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
