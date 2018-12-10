package com.example.khong.term4_java1d;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.sackcentury.shinebuttonlib.ShineButton;

public class CustomShineButton extends ShineButton {
    boolean NotifyStatus = true;
    TextView NotifyState;

    // hardcode expected number
    private int numWasher = 12;
    private int numDryer = 9;

    private FirebaseController firebaseController = FirebaseController.getInstance();
    private DatabaseReference userDatabase;
    private DatabaseReference userTopicChoiceRef;

    public CustomShineButton(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    void setUnavailable() {
        if (!this.NotifyStatus) {
            this.NotifyState.setText(R.string.notification_unavailable);
            this.setBtnFillColor(0xFFA0A0A0);
            this.setShapeResource(R.drawable.ic_assets_disabledbell);
            this.setChecked(true);
            this.setClickable(false);
        } else {
            this.setShapeResource(R.drawable.ic_assets_whitebell);
            this.setClickable(true);
        }
    }

    void dryerOnClickFunction(String block) {
        String block_ = block.substring(6, 8);

        userDatabase = firebaseController.getUserDatabase();
        userTopicChoiceRef = userDatabase.child("subscriptions");

        if (this.isChecked()) {
            this.NotifyState.setText(R.string.notification_enabled);
            for (int i = 1; i < (numDryer + 1); i++) {
                String topic;
                if (i < 10) {
                    topic = block_ + "_d_0" + i;
                } else {
                    topic = block_ + "_d_" + i;
                }
                firebaseController.subscribeTopic(topic);
                userTopicChoiceRef.child(topic).setValue("true");
            }
            Toast.makeText(getContext(), R.string.activated_dryer_toast_display, Toast.LENGTH_SHORT).show();
        } else {
            this.NotifyState.setText(R.string.notification_disabled);
            for (int i = 1; i < (numDryer + 1); i++) {
                String topic;
                if (i < 10) {
                    topic = block_ + "_d_0" + i;
                } else {
                    topic = block_ + "_d_" + i;
                }
                firebaseController.unsubscribeTopic(topic);
                userTopicChoiceRef.child(topic).setValue("false");
            }
            Toast.makeText(getContext(), R.string.deactivated_dryer_toast_display, Toast.LENGTH_SHORT).show();
        }

    }

    void washerOnClickFunction(String block) {
        String block_ = block.substring(6, 8);

        userDatabase = firebaseController.getUserDatabase();
        userTopicChoiceRef = userDatabase.child("subscriptions");

        if (this.isChecked()) {
            this.NotifyState.setText(R.string.notification_enabled);
            for (int i = 0; i < (numWasher + 1); i++) {
                String topic;
                if (i < 10) {
                    topic = block_ + "_w_0" + i;
                } else {
                    topic = block_ + "_w_" + i;
                }
                firebaseController.subscribeTopic(topic);
                userTopicChoiceRef.child(topic).setValue("true");
            }
            Toast.makeText(getContext(), R.string.activated_dryer_toast_display, Toast.LENGTH_SHORT).show();
        } else {
            this.NotifyState.setText(R.string.notification_disabled);
            for (int i = 0; i < (numWasher + 1); i++) {
                String topic;
                if (i < 10) {
                    topic = block_ + "_w_0" + i;
                } else {
                    topic = block_ + "_w_" + i;
                }
                firebaseController.unsubscribeTopic(topic);
                userTopicChoiceRef.child(topic).setValue("false");
            }
            Toast.makeText(getContext(), R.string.deactivated_dryer_toast_display, Toast.LENGTH_SHORT).show();
        }

    }
}
