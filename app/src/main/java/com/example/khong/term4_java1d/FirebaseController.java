package com.example.khong.term4_java1d;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

class FirebaseController {

    private FirebaseAuth auth;
    private DatabaseReference userDatabase;
    private DatabaseReference blockDatabase;
    private String userUuid;
    private String userBlockChoice;

    public String getUserUuid() {
        return userUuid;
    }

    String getUserBlockChoice() {
        if (userBlockChoice==null){
            return "block_55";
        } else {
            return userBlockChoice;
        }

    }

    void FirebaseController() {
        auth = FirebaseAuth.getInstance();

        userDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        userUuid = auth.getCurrentUser().getUid();

        DatabaseReference userBlockChoiceRef = userDatabase.child(userUuid).child("block_choice");
        ValueEventListener blockChoiceListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userBlockChoice = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        userBlockChoiceRef.addValueEventListener(blockChoiceListener);
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
