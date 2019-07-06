package cp317.wlu.ca.fridgepal.signupflow;

public class ConfirmClass {
    String mGroceryDay;
    String mDietaryPref;

    public ConfirmClass(String mGroceryDay, String mDietaryPref) {
        this.mGroceryDay = mGroceryDay;
        this.mDietaryPref = mDietaryPref;
    }

    public String getmGroceryDay() {
        return mGroceryDay;
    }

    public void setmGroceryDay(String mGroceryDay) {
        this.mGroceryDay = mGroceryDay;
    }

    public String getmDietaryPref() {
        return mDietaryPref;
    }

    public void setmDietaryPref(String mDietaryPref) {
        this.mDietaryPref = mDietaryPref;
    }
}
