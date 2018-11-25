package com.example.khong.term4_java1d;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

import com.sackcentury.shinebuttonlib.ShineButton;

/**
 * Created by hojin on 22/11/2018.
 */

public class CustomShineButton extends ShineButton {
    public boolean NotifStatus = true;
    public TextView NotifState;

    private SubcriptionsManager subcriptionsManager = new SubcriptionsManager();

    public CustomShineButton(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    public void setUnavailable() {
        if (!this.NotifStatus) {
            this.NotifState.setText(R.string.notification_unavailable);
            this.setBtnFillColor(0xFFA0A0A0);
            this.setShapeResource(R.drawable.ic_assets_disabledbell);
            this.setChecked(true);
            this.setClickable(false);
        } else {
            this.setShapeResource(R.drawable.ic_assets_whitebell);
            this.setClickable(true);
        }
    }

    public void dryerOnClickFunction(String block) {
        if (this.isChecked()) {
            this.NotifState.setText(R.string.notification_enabled);
            for (int i = 0; i < 13; i++) {
                subcriptionsManager.subscribeTopic(block + "_w_" + Integer.toString(i));
            }
            Toast.makeText(getContext(), R.string.activated_dryer_toast_display, Toast.LENGTH_SHORT).show();
        } else {
            this.NotifState.setText(R.string.notification_disabled);
            for (int i = 0; i < 13; i++) {
                subcriptionsManager.unsubscribeTopic(block + "_w_" + Integer.toString(i));
            }
            Toast.makeText(getContext(), R.string.deactivated_dryer_toast_display, Toast.LENGTH_SHORT).show();
        }

    }

    public void washerOnClickFunction(String block) {
        if (this.isChecked()) {
            this.NotifState.setText(R.string.notification_enabled);
            for (int i = 0; i < 13; i++) {
                subcriptionsManager.subscribeTopic(block + "_d_" + Integer.toString(i));
            }
            Toast.makeText(getContext(), R.string.activated_dryer_toast_display, Toast.LENGTH_SHORT).show();
        } else {
            this.NotifState.setText(R.string.notification_disabled);
            for (int i = 0; i < 13; i++) {
                subcriptionsManager.unsubscribeTopic(block + "_d_" + Integer.toString(i));
            }
            Toast.makeText(getContext(), R.string.deactivated_dryer_toast_display, Toast.LENGTH_SHORT).show();
        }

    }
}
