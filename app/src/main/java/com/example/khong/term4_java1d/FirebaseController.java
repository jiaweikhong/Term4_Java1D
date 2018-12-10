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

    private static FirebaseController SingletonInstance = null;
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
        if (SingletonInstance == null) {
            SingletonInstance = new FirebaseController();
        }
        return (SingletonInstance);
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

    void subscribeTopic(String topic_name) {
        Log.d(topic_name, "Subscribe On");
        FirebaseMessaging.getInstance().subscribeToTopic(topic_name)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("FirebaseMessaging", "Successfully subscribed");
                        } else {
                            Log.e("FirebaseMessaging", "Failed to subscribed");
                        }
                    }
                });
    }

    void unsubscribeTopic(String topic_name) {
        Log.d(topic_name, "Subscribe Off");
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic_name)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("FirebaseMessaging", "Successfully unsubscribed");
                        } else {
                            Log.e("FirebaseMessaging", "Failed to unsubscribed");
                        }
                    }
                });
    }

}
