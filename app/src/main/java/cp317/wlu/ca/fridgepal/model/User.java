package cp317.wlu.ca.fridgepal.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String groceryDay;
    public String dietaryPreference;
    public String userId;

    public User() {
    }

    public User(String userId, String groceryDay, String dietaryPreference) {
        this.groceryDay = groceryDay;
        this.dietaryPreference = dietaryPreference;
        this.userId = userId;
    }
}
