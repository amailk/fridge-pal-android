package cp317.wlu.ca.fridgepal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
    String name;

    int amount;

    QuantityType quantityType;

    public Ingredient(String name, QuantityType quantityType, int amount) {
        this.name = name;
        this.quantityType = quantityType;
        this.amount = amount;
    }

    protected Ingredient(Parcel in) {
        name = in.readString();
        amount = in.readInt();
        quantityType = QuantityType.values()[in.readInt()];
    }

    public String getName() {
        return name;
    }

    public QuantityType getQuantityType() {
        return quantityType;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(amount);
        dest.writeInt(quantityType.ordinal());
    }

    public enum QuantityType {
        ML,
        CUPS,
        OZ,
        KG,
        G,
        TSP,
        TBSP
    }
}