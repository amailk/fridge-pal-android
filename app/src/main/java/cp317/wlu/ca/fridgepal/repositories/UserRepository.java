package cp317.wlu.ca.fridgepal.repositories;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;

import cp317.wlu.ca.fridgepal.model.User;

public class UserRepository {
    private static UserRepository instance = null;
    private final FirebaseAuth firebaseAuth;

    private FirebaseUser user;
    private DatabaseReference databaseReference;

    private UserRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public static UserRepository getInstance()  {
        if(instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void refreshCurrentUser() {
        this.user = firebaseAuth.getCurrentUser();
    }

    public void saveUserPreferences(String groceryDay, String dietaryPreference) {
        User userConfigurationToSave = new User(user.getUid(), groceryDay, dietaryPreference);
        databaseReference.child("users").child(user.getUid()).setValue(userConfigurationToSave);
    }

    public void hasUserCompletedSignUpFlow(Consumer<Boolean> result) {
        databaseReference.child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                result.accept(dataSnapshot != null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                result.accept(false);
            }
        });
    }

    public FirebaseUser getUser() {
        return user;
    }
}
