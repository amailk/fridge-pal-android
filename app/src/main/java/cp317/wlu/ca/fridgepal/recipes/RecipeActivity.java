package cp317.wlu.ca.fridgepal.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Recipe;

public class RecipeActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE_ID = "extra_recipe_id";
    private static final String TAG = RecipeActivity.class.getSimpleName();

    private String recipeId;
    private RecipesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        recipeId = getIntent().getExtras().getString(EXTRA_RECIPE_ID);
        viewModel = ViewModelProviders.of(this).get(RecipesViewModel.class);

        viewModel.fetchSelectedRecipe(recipeId);
        viewModel.getSelectedRecipeLiveData().observe(this, recipe -> populateViews(recipe));

        ViewPager viewPager = findViewById(R.id.container);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = findViewById(R.id.recipe_tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void populateViews(Recipe recipe) {
        TextView title = findViewById(R.id.title);
        title.setText(recipe.getTitle());

        ImageView imageView = findViewById(R.id.image);
        Picasso.get()
                .load(recipe.getImage())
                .fit()
                .centerCrop()
                .into(imageView);

        findViewById(R.id.button_more_like_this).setOnClickListener(v -> {
            Intent intent = new Intent(this, RelatedRecipesActivity.class);
            intent.putExtra(RelatedRecipesActivity.EXTRA_RECIPE_ID, recipeId);
            startActivity(intent);
        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return IngredientsFragment.newInstance();
                case 1:
                    return InstructionsFragment.newInstance();
                default:
                    Log.e(TAG, "Invalid page for SectionsPagerAdapter");
                    return IngredientsFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
