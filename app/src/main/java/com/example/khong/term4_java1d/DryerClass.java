package com.example.khong.term4_java1d;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class DryerClass {

    ImageView notifImage;
    Boolean notifStatus;

    public DryerClass () {
        this.notifStatus = false;
    }

    View.OnClickListener dryerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (notifStatus == true) {
                notifImage.setImageResource(R.drawable.ic_assets_lightbluebell);
                notifStatus = false;
            } else {
                notifImage.setImageResource(R.drawable.ic_assets_darkbluebell);
                notifStatus = true;
            }
        }
    };
}
