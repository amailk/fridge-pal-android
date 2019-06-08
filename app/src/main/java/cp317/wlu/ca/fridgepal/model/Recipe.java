package cp317.wlu.ca.fridgepal.model;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Recipe {
    String name;
    String description;
    Drawable image;


    public Recipe(String name, String description, Drawable image) {

        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
    public Drawable getImage() { return image; }


}
