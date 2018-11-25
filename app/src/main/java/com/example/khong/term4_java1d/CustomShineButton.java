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
        }
    }

    public void DryerOnClickFunction() {
        if (this.NotifStatus) {
            this.setChecked(true);
            Toast.makeText(getContext(), R.string.disabled_dryer_toast_display, Toast.LENGTH_LONG).show();
        }
        if (this.isChecked() && !this.NotifStatus) {
            Toast.makeText(getContext(), R.string.activated_dryer_toast_display, Toast.LENGTH_LONG).show();
            this.NotifState.setText(R.string.notification_enabled);
        } else if (!this.NotifStatus) {
            Toast.makeText(getContext(), R.string.deactivated_dryer_toast_display, Toast.LENGTH_LONG).show();
            this.NotifState.setText(R.string.notification_disabled);
        }

    }

    public void WasherOnClickFunction() {
        if (this.NotifStatus) {
            this.setChecked(true);
            Toast.makeText(getContext(), R.string.disabled_washer_toast_display, Toast.LENGTH_LONG).show();
        }
        if (this.isChecked() && !this.NotifStatus) {
            Toast.makeText(getContext(), R.string.activated_washer_toast_display, Toast.LENGTH_LONG).show();
            this.NotifState.setText(R.string.notification_enabled);
        } else if (!this.NotifStatus) {
            Toast.makeText(getContext(), R.string.deactivated_washer_toast_display, Toast.LENGTH_LONG).show();
            this.NotifState.setText(R.string.notification_disabled);
        }

    }
}
