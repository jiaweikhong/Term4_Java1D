package com.example.khong.term4_java1d;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class Washer extends AppCompatActivity {
    private long startTimeInMillis = 600000;
    private CountDownTimer washerCountdownTimer;

    private TextView Washer1st_timevalue;
    private TextView mTextMessage;
    private ImageView Washer1st_notif;
    private Boolean Washer1st_notifstatus = false;
    private SwipeRefreshLayout refreshWasher;
    private ScrollView washerScrollView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washer);
        //testing out setting a countdowntimer


        //these are for swiperefresh
        refreshWasher = findViewById(R.id.refreshWasher);
        washerScrollView = findViewById(R.id.washerScrollView);
        refreshWasher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //get firebase values
                Intent intent = new Intent(Washer.this, Washer.class);
                startActivity(intent);
                Washer.this.finish();
                refreshWasher.setRefreshing(false);
            }
        });
        //these are for swipe refresh


        // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_washer);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Instantiate all washers here.
        MachineClass W01 = new MachineClass(10000);
        W01.notifImage = findViewById(R.id.Washer1st_notif);
        W01.notifImage.setOnClickListener(W01.machineOnClickListener);
        W01.machine_timevalue = findViewById(R.id.Washer1st_timevalue);
        W01.machine_timestatus = findViewById(R.id.Washer1st_timestatus);
        //

        MachineClass W02 = new MachineClass();
        W02.notifImage = findViewById(R.id.Washer2nd_notif);
        W02.notifImage.setOnClickListener(W02.machineOnClickListener);
        W02.machine_timevalue = findViewById(R.id.Washer2nd_timevalue);
        W02.machine_timestatus = findViewById(R.id.Washer2nd_timestatus);


        MachineClass W03 = new MachineClass(10000);
        W03.notifImage = findViewById(R.id.Washer3rd_notif);
        W03.notifImage.setOnClickListener(W03.machineOnClickListener);
        W03.machine_timevalue = findViewById(R.id.Washer3rd_timevalue);
        W03.machine_timestatus = findViewById(R.id.Washer3rd_timestatus);

        MachineClass W04 = new MachineClass();
        W04.notifImage = findViewById(R.id.Washer4th_notif);
        W04.notifImage.setOnClickListener(W04.machineOnClickListener);
        W04.machine_timevalue = findViewById(R.id.Washer4th_timevalue);
        W04.machine_timestatus = findViewById(R.id.Washer4th_timestatus);

        MachineClass W05 = new MachineClass();
        W05.notifImage = findViewById(R.id.Washer5th_notif);
        W05.notifImage.setOnClickListener(W05.machineOnClickListener);
        W05.machine_timevalue = findViewById(R.id.Washer5th_timevalue);
        W05.machine_timestatus = findViewById(R.id.Washer5th_timestatus);


        MachineClass W06 = new MachineClass();
        W06.notifImage = findViewById(R.id.Washer6th_notif);
        W06.notifImage.setOnClickListener(W06.machineOnClickListener);
        W06.machine_timevalue = findViewById(R.id.Washer6th_timevalue);
        W06.machine_timestatus = findViewById(R.id.Washer6th_timestatus);

        MachineClass W07 = new MachineClass();
        W07.notifImage = findViewById(R.id.Washer7th_notif);
        W07.notifImage.setOnClickListener(W07.machineOnClickListener);
        W07.machine_timevalue = findViewById(R.id.Washer7th_timevalue);
        W07.machine_timestatus = findViewById(R.id.Washer7th_timestatus);

        MachineClass W08 = new MachineClass();
        W08.notifImage = findViewById(R.id.Washer8th_notif);
        W08.notifImage.setOnClickListener(W08.machineOnClickListener);
        W08.machine_timevalue = findViewById(R.id.Washer8th_timevalue);
        W08.machine_timestatus = findViewById(R.id.Washer8th_timestatus);


        MachineClass W09 = new MachineClass();
        W09.notifImage = findViewById(R.id.Washer9th_notif);
        W09.notifImage.setOnClickListener(W09.machineOnClickListener);
        W09.machine_timevalue = findViewById(R.id.Washer9th_timevalue);
        W09.machine_timestatus = findViewById(R.id.Washer9th_timestatus);


        MachineClass W10 = new MachineClass();
        W10.notifImage = findViewById(R.id.Washer10th_notif);
        W10.notifImage.setOnClickListener(W10.machineOnClickListener);
        W10.machine_timevalue = findViewById(R.id.Washer10th_timevalue);
        W10.machine_timestatus = findViewById(R.id.Washer10th_timestatus);

        MachineClass W11 = new MachineClass();
        W11.notifImage = findViewById(R.id.Washer11th_notif);
        W11.notifImage.setOnClickListener(W11.machineOnClickListener);
        W11.machine_timevalue = findViewById(R.id.Washer11th_timevalue);
        W11.machine_timestatus = findViewById(R.id.Washer11th_timestatus);

        MachineClass W12 = new MachineClass();
        W12.notifImage = findViewById(R.id.Washer12th_notif);
        W12.notifImage.setOnClickListener(W12.machineOnClickListener);
        W12.machine_timevalue = findViewById(R.id.Washer12th_timevalue);
        W12.machine_timestatus = findViewById(R.id.Washer12th_timestatus);

    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Logout) {
            Intent intent = new Intent(Washer.this, LogoutActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Washer.this.finish();

            return true;
        }

        if (id == R.id.ChooseBlock) {
            Intent intent = new Intent(Washer.this, ChooseBlock.class);
            startActivity(intent);
            Washer.this.finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dryer:
                    // mTextMessage.setText(R.string.title_home);
                    Intent intent = new Intent(Washer.this, Dryer.class);
                    startActivity(intent);
                    Washer.this.finish();

                    break;
                case R.id.navigation_washer:
                    // mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_main:
                    Intent intentToMain = new Intent(Washer.this, MainActivity.class);
                    startActivity(intentToMain);
                    Washer.this.finish();

                    break;
            }
            return false;
        }
    };
    private void updateCountdownText(){
        int minutes = (int)(startTimeInMillis/1000)/60;
        int seconds = (int)(startTimeInMillis/1000)%60;
        String timeleftFormatted = String.format("%02d:%02d",minutes,seconds);
        Washer1st_timevalue.setText(timeleftFormatted);
    }

}
