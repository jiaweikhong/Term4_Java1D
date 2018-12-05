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

    private DatabaseReference userDatabase;
    private String userUuid;
    private String userBlockChoice;

    FirebaseController() {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        userDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        userUuid = auth.getCurrentUser().getUid();

    }

    public String getUserUuid() {
        return userUuid;
    }

    void createUserBlockChoiceRef() {
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

    String getUserBlockChoice() {
        return userBlockChoice;
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

    void getSubscribeStatus(final String topic_name) {

    }

}
