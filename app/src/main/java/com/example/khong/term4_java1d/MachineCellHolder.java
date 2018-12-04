package com.example.khong.term4_java1d;

import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MachineCellHolder extends RecyclerView.ViewHolder {

    private CountDownTimer machineCountdownTimer;
    private TextView machineName;
    private TextView machineTimeData;
    private TextView machineTimeLabel;
    private ImageView machineStatus;
    private ImageButton btnMachineNotify;
    private LinearLayout machine_to_detailed_page;
    private boolean notifyState;
    private boolean collected;
    private String machineTopic;
    private FirebaseController firebaseController;
    private DatabaseReference userDatabase;
    private String userUuid;
    private String userTopicChoice;
    private DatabaseReference userTopicChoiceRef;
    private boolean btnNotifyBool;

    MachineCellHolder(final View cellView) {
        super(cellView);

        Log.d("MachineCellHolder", "Created");

        firebaseController = new FirebaseController();

        machineName = cellView.findViewById(R.id.machine_name);
        machineTimeData = cellView.findViewById(R.id.machine_timedata);
        machineTimeLabel = cellView.findViewById(R.id.machine_timelabel);
        machine_to_detailed_page = cellView.findViewById(R.id.machine_to_detailed_page);
        machineStatus = cellView.findViewById(R.id.machine_icon);
        btnMachineNotify = cellView.findViewById(R.id.machine_notifButton);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        userDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        userUuid = auth.getCurrentUser().getUid();
    }

    void turnOnNotifications() {
        userTopicChoiceRef.setValue("true");
        Log.e("MachineCellHolder", "Notifications On");
        btnMachineNotify.setImageResource(R.drawable.ic_assets_darkbluebell);
        firebaseController.subscribeTopic(machineTopic);
    }

    void turnOffNotifications() {
        userTopicChoiceRef.setValue("false");
        Log.e("MachineCellHolder", "Notifications Off");
        btnMachineNotify.setImageResource(R.drawable.ic_assets_lightbluebell);
        firebaseController.unsubscribeTopic(machineTopic);
    }

    void setMachineTimeData(long secondsElapsed) {
        Log.d("MachineCellHolder", "Set Time Data");
        final long startMillis = secondsElapsed * 1000;
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
                    if (collected) {
                        setMachineStatus("GREEN");
                    } else {
                        setMachineStatus("YELLOW");
                        setMachineTimeData(startMillis / 1000);
                    }
                }
            }.start();
        } else {
            setMachineTimeLabel("since cycle finished");
            setMachineStatus("YELLOW");
            if (collected) {
                // confirm collected
                setMachineStatus("GREEN");
            } else if (secondsElapsed > (2 * 60 * 60)) {
                // 2 hours later, probably collected
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
                    // it's a count up timer, and should never actually finish!
                }
            }.start();
        }
    }

    void setMachineStatus(String machineStatus) {
        if (machineStatus.equals("GREEN")) {
            Log.d("MachineCellHolder", "Set Status Green");
            this.machineStatus.setImageResource(R.drawable.ic_assets_greencircle);
            turnOffNotifications();
            this.btnMachineNotify.setEnabled(false);
            notifyState = false;
        } else if (machineStatus.equals("YELLOW")) {
            Log.d("MachineCellHolder", "Set Status Yellow");
            this.machineStatus.setImageResource(R.drawable.ic_assets_yellowcircle);
            this.btnMachineNotify.setEnabled(true);
        } else {
            Log.d("MachineCellHolder", "Set Status Red");
            this.machineStatus.setImageResource(R.drawable.ic_assets_redcircle);
            this.btnMachineNotify.setEnabled(true);
        }
    }

    void setMachineName(String machineName) {
        this.machineName.setText(machineName);
    }

    void setMachineTimeLabel(String machineTimeLabel) {
        this.machineTimeLabel.setText(machineTimeLabel);
    }

    private String formatTimeData(long timeData) {
        long secondData = timeData / 1000;
        int mh_raw = (int) (secondData) / 60;
        int hours = mh_raw / 60;
        int minutes = mh_raw % 60;
        int seconds = (int) (secondData) % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    String getMachineTopic() {
        return machineTopic;
    }

    void setMachineTopic(final String machineTopic) {
        Log.d("MachineCellHolder", "Set Topic");
        this.machineTopic = machineTopic;

        Log.d("MachineCellHolder", userUuid);
        Log.d("MachineCellHolder", machineTopic);

        userTopicChoiceRef = userDatabase.child(userUuid).child("subscriptions").child(machineTopic);
        ValueEventListener topicChoiceListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    userTopicChoice = dataSnapshot.getValue().toString();
                    if (userTopicChoice.equals("true")) {
                        notifyState = true;
                    } else {
                        notifyState = false;
                    }
                } catch (NullPointerException e) {
                    Log.e("MachineCellHolder", "Null preference, set to false");
                    userTopicChoiceRef.setValue("false");
                    notifyState = false;
                } finally {
                    if (notifyState) {
                        btnNotifyBool = true;
                        turnOnNotifications();
                    } else {
                        btnNotifyBool = false;
                        turnOffNotifications();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        userTopicChoiceRef.addListenerForSingleValueEvent(topicChoiceListener);


        View.OnClickListener machineNotifyOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnNotifyBool) {
                    turnOffNotifications();
                    btnNotifyBool = false;
                } else {
                    btnNotifyBool = true;
                    turnOnNotifications();
                }
            }
        };

        Log.e("MachineCellHolder", "Set btnMachineNotify listener");
        btnMachineNotify.setOnClickListener(machineNotifyOnClickListener);


    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        Log.d("MachineCellHolder", "Set Collected");
        this.collected = collected;

        if (collected) {
            setMachineStatus("GREEN");
            btnMachineNotify.setEnabled(false);
        } else {
            btnMachineNotify.setEnabled(true);
        }

    }


}
