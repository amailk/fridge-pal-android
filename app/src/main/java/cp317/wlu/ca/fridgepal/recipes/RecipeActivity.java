package cp317.wlu.ca.fridgepal.recipes;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Recipe;

public class RecipeActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "extra_recipe";
    private Recipe recipe;
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
        title.setText(recipe.getName());

        ImageView image = findViewById(R.id.image);
        image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), recipe.getImage(), null));

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
