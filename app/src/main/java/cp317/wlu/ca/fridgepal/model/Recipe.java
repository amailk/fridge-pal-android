package cp317.wlu.ca.fridgepal.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {
    String name;
    String description;
    @DrawableRes
    int image;
    List<Ingredient> ingredients;


    public Recipe(String name, String description, @DrawableRes int image, List<Ingredient> ingredients) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public @DrawableRes
    int getImage() {
        return image;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    protected Recipe(Parcel in) {
        name = in.readString();
        description = in.readString();
        image = in.readInt();
        if (in.readByte() == 0x01) {
            ingredients = new ArrayList<Ingredient>();
            in.readList(ingredients, Ingredient.class.getClassLoader());
        } else {
            ingredients = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(image);
        if (ingredients == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(ingredients);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}