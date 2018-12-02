package com.example.khong.term4_java1d;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MachineCellHolder extends RecyclerView.ViewHolder {

    private CountDownTimer machineCountdownTimer;
    private TextView machineName;
    private TextView machineTimeData;
    private TextView machineTimeLabel;
    private ImageView machineStatus;
    private ImageButton btnMachineNotify;
    private boolean notifyState;

    MachineCellHolder(final View cellView) {
        super(cellView);
        machineName = cellView.findViewById(R.id.machine_name);
        machineTimeData = cellView.findViewById(R.id.machine_timedata);
        machineTimeLabel = cellView.findViewById(R.id.machine_timelabel);

        machineStatus = cellView.findViewById(R.id.machine_icon);
        btnMachineNotify = cellView.findViewById(R.id.machine_notifButton);

        View.OnClickListener machineNotifyOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notifyState == true) {
                    btnMachineNotify.setImageResource(R.drawable.ic_assets_lightbluebell);
                    notifyState = false;
                } else  {
                    btnMachineNotify.setImageResource(R.drawable.ic_assets_darkbluebell);
                    notifyState = true;
                }
            }
        };

        btnMachineNotify.setOnClickListener(machineNotifyOnClickListener);

    }

    public void setMachineName(String machineName) {
        this.machineName.setText(machineName);
    }

    public void setMachineTimeData(long secondsElapsed) {

        long startMillis = secondsElapsed * 1000;

        machineCountdownTimer = null;

        if (secondsElapsed < (45 * 60)) {

            long timeToComplete = (45 * 60) - secondsElapsed;

            setMachineTimeLabel("since cycle start");
            setMachineStatus("RED");

            machineCountdownTimer = new CountDownTimer(timeToComplete * 1000, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    machineTimeData.setText(formatTimeData(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    setMachineTimeLabel("wash cycle finished");
                }

            }.start();

        } else {

            setMachineTimeLabel("since cycle finished");
            setMachineStatus("YELLOW");

            if (secondsElapsed > (60 * 60)) {
                setMachineStatus("GREEN");
            }

            machineCountdownTimer = new CountDownTimer(startMillis - (45 * 60 * 1000), 1000) {

                long counter = 0;
                long timeData = 0;

                @Override
                public void onTick(long millisUntilFinished) {
                    counter = counter + 2000;
                    timeData = millisUntilFinished + counter;
                    machineTimeData.setText(formatTimeData(timeData));
                }

                @Override
                public void onFinish() {

                }

            }.start();
        }

    }

    private String formatTimeData(long timeData) {
        long secondData = timeData / 1000;
        int mh_raw = (int) (secondData) / 60;
        int hours = mh_raw / 60;
        int minutes = mh_raw % 60;
        int seconds = (int) (secondData) % 60;
        String timeLeftFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);

        return timeLeftFormatted;
    }

    public void setMachineTimeLabel(String machineTimeLabel) {
        this.machineTimeLabel.setText(machineTimeLabel);
    }

    public void setMachineStatus(String machineStatus) {
        if (machineStatus.equals("GREEN")) {
            this.machineStatus.setImageResource(R.drawable.ic_assets_greencircle);
            this.btnMachineNotify.setEnabled(false);
        } else if (machineStatus.equals("YELLOW")) {
            this.machineStatus.setImageResource(R.drawable.ic_assets_yellowcircle);
            this.btnMachineNotify.setEnabled(false);
        } else {
            this.machineStatus.setImageResource(R.drawable.ic_assets_redcircle);
            this.btnMachineNotify.setEnabled(true);
        }
    }


}
