package com.example.khong.term4_java1d;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hojin on 25/11/2018.
 */

public class MachineClass extends AppCompatActivity{

    long startTimeInMillis = 600000;
    ImageView notifImage;
    Boolean notifStatus;
    TextView machine_timevalue;
    CountDownTimer machineCountdownTimer;
    TextView machine_timestatus;


    public MachineClass () {
        this.notifStatus = false;
        machineCountdownTimer = new CountDownTimer(startTimeInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                startTimeInMillis=millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                machine_timestatus.setText(R.string.available_time_status);
            }

        }.start();
    }
    public MachineClass (long startTimeInMilli) {
        this.startTimeInMillis = startTimeInMilli;
        this.notifStatus = false;
        machineCountdownTimer = new CountDownTimer(startTimeInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                startTimeInMillis=millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                machine_timestatus.setText(R.string.available_time_status);
            }

        }.start();
    }
    View.OnClickListener machineOnClickListener = new View.OnClickListener() {
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
        this.machine_timevalue.setText(timeleftFormatted);
    }
}
