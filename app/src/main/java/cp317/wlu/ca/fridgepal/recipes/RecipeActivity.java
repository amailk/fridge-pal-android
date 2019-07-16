package cp317.wlu.ca.fridgepal.recipes;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.IngredientRecipe;
import cp317.wlu.ca.fridgepal.model.Recipe;

public class RecipeActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "extra_recipe";
    private IngredientRecipe recipe;
    private ViewPager viewPager;
    private RecipesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipe = getIntent().getExtras().getParcelable(EXTRA_RECIPE);

        viewModel = ViewModelProviders.of(this).get(RecipesViewModel.class);
        viewModel.setSelectedRecipe(recipe);

        TextView title = findViewById(R.id.title);
        title.setText(recipe.getTitle());

        ImageView image = findViewById(R.id.image);
        //TODO

        viewPager = findViewById(R.id.container);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = findViewById(R.id.recipe_tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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
                    return IngredientsFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
