package com.example.khong.term4_java1d;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

public class Dryer extends AppCompatActivity {
    private SwipeRefreshLayout refreshDryer;

    private ScrollView dryerScrollView;
    private TextView mTextMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dryer);
        refreshDryer = findViewById(R.id.refreshDryer);
        dryerScrollView = findViewById(R.id.dryerScrollView);
        refreshDryer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //get firebase values
                Intent intent = new Intent(Dryer.this, Dryer.class);
                startActivity(intent);
                Dryer.this.finish();
                refreshDryer.setRefreshing(false);
            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_dryer);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Instantiate all washers here.
        MachineClass D01 = new MachineClass();
        D01.notifImage = findViewById(R.id.Dryer1st_notif);
        D01.notifImage.setOnClickListener(D01.machineOnClickListener);
        D01.machine_timevalue = findViewById(R.id.Dryer1st_timevalue);
        D01.machine_timestatus = findViewById(R.id.Dryer1st_timestatus);

        MachineClass D02 = new MachineClass();
        D02.notifImage = findViewById(R.id.Dryer2nd_notif);
        D02.notifImage.setOnClickListener(D02.machineOnClickListener);
        D02.machine_timevalue = findViewById(R.id.Dryer2nd_timevalue);
        D02.machine_timestatus = findViewById(R.id.Dryer2nd_timestatus);


        MachineClass D03 = new MachineClass();
        D03.notifImage = findViewById(R.id.Dryer3rd_notif);
        D03.notifImage.setOnClickListener(D03.machineOnClickListener);
        D03.machine_timevalue = findViewById(R.id.Dryer3rd_timevalue);
        D03.machine_timestatus = findViewById(R.id.Dryer3rd_timestatus);


        MachineClass D04 = new MachineClass();
        D04.notifImage = findViewById(R.id.Dryer4th_notif);
        D04.notifImage.setOnClickListener(D04.machineOnClickListener);
        D04.machine_timevalue = findViewById(R.id.Dryer4th_timevalue);
        D04.machine_timestatus = findViewById(R.id.Dryer4th_timestatus);


        MachineClass D05 = new MachineClass();
        D05.notifImage = findViewById(R.id.Dryer5th_notif);
        D05.notifImage.setOnClickListener(D05.machineOnClickListener);
        D05.machine_timevalue = findViewById(R.id.Dryer5th_timevalue);
        D05.machine_timestatus = findViewById(R.id.Dryer5th_timestatus);


        MachineClass D06 = new MachineClass();
        D06.notifImage = findViewById(R.id.Dryer6th_notif);
        D06.notifImage.setOnClickListener(D06.machineOnClickListener);
        D06.machine_timevalue = findViewById(R.id.Dryer6th_timevalue);
        D06.machine_timestatus = findViewById(R.id.Dryer6th_timestatus);

        MachineClass D07 = new MachineClass();
        D07.notifImage = findViewById(R.id.Dryer7th_notif);
        D07.notifImage.setOnClickListener(D07.machineOnClickListener);
        D07.machine_timevalue = findViewById(R.id.Dryer7th_timevalue);
        D07.machine_timestatus = findViewById(R.id.Dryer7th_timestatus);

        MachineClass D08 = new MachineClass();
        D08.notifImage = findViewById(R.id.Dryer8th_notif);
        D08.notifImage.setOnClickListener(D08.machineOnClickListener);
        D08.machine_timevalue = findViewById(R.id.Dryer8th_timevalue);
        D08.machine_timestatus = findViewById(R.id.Dryer8th_timestatus);


        MachineClass D09 = new MachineClass();
        D09.notifImage = findViewById(R.id.Dryer9th_notif);
        D09.notifImage.setOnClickListener(D09.machineOnClickListener);
        D09.machine_timevalue = findViewById(R.id.Dryer9th_timevalue);
        D09.machine_timestatus = findViewById(R.id.Dryer9th_timestatus);



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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.Logout) {
            //Write the intent here
            Intent intent = new Intent(Dryer.this, LogoutActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Dryer.this.finish();
            return true;
        }

        if (id == R.id.ChooseBlock) {
            Intent intent = new Intent(Dryer.this, ChooseBlock.class);
            startActivity(intent);
            Dryer.this.finish();
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
                    return true;
                case R.id.navigation_washer:
                    // mTextMessage.setText(R.string.title_dashboard);
                    Intent intent = new Intent(Dryer.this, Washer.class);
                    startActivity(intent);
                    Dryer.this.finish();
                    break;
                case R.id.navigation_main:
                    Intent intentToMain = new Intent(Dryer.this, MainActivity.class);
                    startActivity(intentToMain);
                    Dryer.this.finish();
                    break;
            }
            return false;
        }
    };
}
