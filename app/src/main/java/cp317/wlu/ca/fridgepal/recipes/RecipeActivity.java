package cp317.wlu.ca.fridgepal.recipes;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
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

        ImageView fav_img = findViewById(R.id.favRecipe_button);
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
