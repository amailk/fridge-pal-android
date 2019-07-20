package cp317.wlu.ca.fridgepal.recipes;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import cp317.wlu.ca.fridgepal.R;

public class RelatedRecipesActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE_ID = "extra_recipe_id";
    private static final String TAG = RelatedRecipesActivity.class.getSimpleName();

    String recipeId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_related_recipes);

        recipeId = getIntent().getExtras().getString(EXTRA_RECIPE_ID);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        RecipesFragment fragment = RecipesFragment.newInstanceWithRelatedRecipesForRecipe(recipeId);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}
