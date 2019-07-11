package cp317.wlu.ca.fridgepal.signupflow;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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


}
