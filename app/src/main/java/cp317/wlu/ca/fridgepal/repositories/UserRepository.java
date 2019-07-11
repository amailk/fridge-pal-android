package cp317.wlu.ca.fridgepal.repositories;

import com.google.firebase.auth.FirebaseUser;

public class UserRepository {
    private static UserRepository instance = null;
    private FirebaseUser user;

    private UserRepository() {

    }

    public static UserRepository getInstance()  {
        if(instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void setCurrentUser(FirebaseUser user) {
        this.user = user;
    }
}
