package cp317.wlu.ca.fridgepal.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class Food {
    private String name;
    private String addedDate;
    private String expiryDate;
    private String category;
    @Exclude
    private String Uuid;

    public Food() {
        // Default constructor required for Firebase
    }

    public Food(String name, String category, String expiryDate) {
        this.name = name;
        this.category = category;
        this.expiryDate = expiryDate;
        this.addedDate = new Date().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Exclude
    public String getUuid() {
        return Uuid;
    }

    @Exclude
    public void setUuid(String uuid) {
        Uuid = uuid;
    }
}