package cp317.wlu.ca.fridgepal;

import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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
    }
}
