package cp317.wlu.ca.fridgepal.signupflow;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ConfirmViewModel extends ViewModel {

    MutableLiveData<String> mGroceryDayInp;
    MutableLiveData<String> mDietPrefInp;

    public void init(){
        mGroceryDayInp = new MutableLiveData<>();
        mGroceryDayInp.setValue("Default Message");

        mDietPrefInp = new MutableLiveData<>();
        mDietPrefInp.setValue("Default Message");
    }

    public MutableLiveData<String> getmDietPrefInp() {
        return mDietPrefInp;
    }

    public void setmDietPrefInp(MutableLiveData<String> mDietPrefInp) {
        this.mDietPrefInp = mDietPrefInp;
    }

    public MutableLiveData<String> getmGroceryDayInp() {
        return mGroceryDayInp;
    }

    public void setmGroceryDayInp(MutableLiveData<String> mGroceryDayInp) {
        this.mGroceryDayInp = mGroceryDayInp;
    }

    public void setGroceryInput(String msg){
        mGroceryDayInp.setValue(msg);
    }
    public void setDietInput(String msg) {mDietPrefInp.setValue(msg);}

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
