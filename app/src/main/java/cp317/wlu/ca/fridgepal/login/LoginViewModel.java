package cp317.wlu.ca.fridgepal.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cp317.wlu.ca.fridgepal.repositories.UserRepository;

public class LoginViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    LoginViewModel(Application application) {
        super(application);
        userRepository = UserRepository.getInstance();
    }

    public boolean isAlreadyAuthenticated() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentUser != null;
    }

    public void setAuthenticatedUser(FirebaseUser user) {
        userRepository.setCurrentUser(user);
    }

    public boolean hasUserCompletedSignUpFlow() {
        //TODO: Check the database to see if there is an entry for the user.
        return false;
    }
}
