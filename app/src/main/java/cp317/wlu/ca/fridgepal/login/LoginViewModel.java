package cp317.wlu.ca.fridgepal.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public void userAuthenticated() {
        userRepository.refreshCurrentUser();
    }

    public void hasUserCompletedSignUpFlow(Consumer<Boolean> result) {
        userRepository.hasUserCompletedSignUpFlow(result);
    }
}
