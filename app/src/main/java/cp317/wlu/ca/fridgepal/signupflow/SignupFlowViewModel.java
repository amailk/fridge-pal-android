package cp317.wlu.ca.fridgepal.signupflow;

import androidx.lifecycle.ViewModel;

import cp317.wlu.ca.fridgepal.repositories.UserRepository;

public class SignupFlowViewModel extends ViewModel {

    private String groceryDay;
    private String dietaryPreference;

    private UserRepository userRepository;

    public SignupFlowViewModel() {
        userRepository = UserRepository.getInstance();
    }

    public String getGroceryDay() {
        return groceryDay;
    }

    void setGroceryDay(String groceryDay) {
        this.groceryDay = groceryDay;
    }

    public String getDietaryPreference() {
        return dietaryPreference;
    }

    void setDietaryPreference(String dietaryPreference) {
        this.dietaryPreference = dietaryPreference;
    }

    void signUpFlowComplete() {
        userRepository.saveUserPreferences(groceryDay, dietaryPreference);
    }
}
