package cp317.wlu.ca.fridgepal.repositories.local;

import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;

import java.util.Arrays;
import java.util.List;

import cp317.wlu.ca.fridgepal.R;
import cp317.wlu.ca.fridgepal.model.Recipe;
import cp317.wlu.ca.fridgepal.repositories.RecipeRepository;


public class LocalRecipeRepository implements RecipeRepository {

    private final Resources resources;

    public LocalRecipeRepository(Resources resources) {
        this.resources = resources;
    }

    @Override
    public List<Recipe> getRecipes() {
        return Arrays.asList(
                new Recipe(
                        "Raspberry Pie",
                        "Preheat oven to 425 degrees F. Mix together the raspberries, sugar, lemon juice, cinnamon and salt until raspberries are well covered. egg with water in a small bowl. Roll out 1 disc of all-shortening pastry into a 13-in. circle on a lightly floured surface. Lift onto a 9-in. pie plate set on a baking sheet. Press dough over bottom and up sides of plate, leaving a 1-in. overhang. Scrape raspberry mixture into pastry. Brush overhang with egg wash. Roll out second pastry disc into a 14-in. circle, about 1/8 in. thick. Cut into 6  2-in. strips. Make a lattice top",
                        R.drawable.raspberry),
                new Recipe(
                        "Thai Fried Rice",
                        "Peel 2 cloves of garlic, and then just finely mince them. Slice ¼ of a sweet white onion into medium sized strips. Finely dice about 3 - 4 green onions",
                        R.drawable.friedrice),
                new Recipe(
                        "Spaghetti",
                        "Heat the olive oil in a large saucepan over medium heat, and cook the onion until lightly brown. Mix in 2 cloves garlic, and cook 1 minute. Stir in crushed tomatoes, tomato paste, water, sugar, 1/2 the oregano, and bay leaf. Season with salt and pepper. Bring to a boil, reduce heat to low, and simmer while preparing meatballs.",
                        R.drawable.spaghetti),
                new Recipe(
                        "Chocolate Cake",
                        "In a large bowl, stir together the sugar, flour, cocoa, baking powder, baking soda and salt. Add the eggs, milk, oil and vanilla, mix for 2 minutes on medium speed of mixer. Stir in the boiling water last. Batter will be thin. Pour evenly into the prepared pans.",
                        R.drawable.chocolate),
                new Recipe(
                        "Quiche",
                        "Preheat oven to 375 degrees F (190 degrees C). Lightly grease a 10 inch quiche dish. In a large bowl, beat together milk, eggs, baking mix, butter and parmesan cheese. Batter will be lumpy. Stir in broccoli, ham and Cheddar cheese. Pour into prepared quiche dish. Bake in preheated oven for 50 minutes, until eggs are set and top is golden brown.",
                        R.drawable.quiche));
    }
}
