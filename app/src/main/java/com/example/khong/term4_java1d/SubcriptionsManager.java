package com.example.khong.term4_java1d;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class SubcriptionsManager {

    protected void subscribeTopic(final String topic_name) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic_name)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // success
                    }
                });
    }

    protected void unsubscribeTopic(final String topic_name) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic_name)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // failure
                    }
                });
    }

}
