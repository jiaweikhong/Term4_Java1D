package com.example.khong.term4_java1d;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WasherClass extends AppCompatActivity {

    long startTimeInMillis = 600000;
    ImageView notifImage;
    Boolean notifStatus;
    TextView Washer_timevalue;
    CountDownTimer washerCountdownTimer;


    public WasherClass () {
        this.notifStatus = false;
        washerCountdownTimer = new CountDownTimer(startTimeInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                startTimeInMillis=millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {

            }

        }.start();
    }

    View.OnClickListener washerOnClickListener = new View.OnClickListener() {
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
    private void updateCountdownText(){
        int minutes = (int)(startTimeInMillis/1000)/60;
        int seconds = (int)(startTimeInMillis/1000)%60;
        String timeleftFormatted = String.format("%02d:%02d",minutes,seconds);
        this.Washer_timevalue.setText(timeleftFormatted);
    }
}
