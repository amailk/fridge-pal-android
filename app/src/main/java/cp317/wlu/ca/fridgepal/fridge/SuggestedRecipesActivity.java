package cp317.wlu.ca.fridgepal.fridge;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.recipes.RecipesFragment;

public class SuggestedRecipesActivity extends AppCompatActivity {

    public static final String EXTRA_INGREDIENT_NAME = "extra_ingredient_name";
    private static final String TAG = SuggestedRecipesActivity.class.getSimpleName();

    String ingredientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        ingredientName = getIntent().getExtras().getString(EXTRA_INGREDIENT_NAME);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        RecipesFragment fragment = RecipesFragment.newInstanceWithSuggestedRecipesForIngredient(ingredientName);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

}
