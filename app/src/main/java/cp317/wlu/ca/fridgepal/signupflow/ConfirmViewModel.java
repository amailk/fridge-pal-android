package cp317.wlu.ca.fridgepal.signupflow;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ConfirmViewModel extends ViewModel {

     MutableLiveData<String> mGroceryDayInp= new MutableLiveData<>();

    public MutableLiveData<String> getmGroceryDayInp() {
        return mGroceryDayInp;
    }

    public void setmGroceryDayInp(MutableLiveData<String> mGroceryDayInp) {
        this.mGroceryDayInp = mGroceryDayInp;
    }

    //public String mDietaryPref="";

    /*public ConfirmViewModel(String mGroceryDay, String mDietaryPref) {
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
    }*/
}
