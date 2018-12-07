package com.example.khong.term4_java1d;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

class FirebaseController {

    private static FirebaseController INSTANCE = null;
    private String userUuid;
    private String userDisplayName;
    private String userEmail;
    private DatabaseReference userDatabase;

    private FirebaseController() {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        userUuid = auth.getCurrentUser().getUid();
        userDisplayName = auth.getCurrentUser().getDisplayName();
        userEmail = auth.getCurrentUser().getEmail();

        userDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(userUuid);

    }

    public static FirebaseController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseController();
        }
        return (INSTANCE);
    }

    public DatabaseReference getUserDatabase() {
        return userDatabase;
    }

    public void setUserDatabase(DatabaseReference userDatabase) {
        Log.e("FirebaseController", "setUserDatabase: Method not allowed.");
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserUuid() {
        return userUuid;
    }

    void subscribeTopic(final String topic_name) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic_name)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // success
                    }
                });
    }

    void unsubscribeTopic(final String topic_name) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic_name)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // failure
                    }
                });
    }

}
