package com.example.khong.term4_java1d;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Dryer extends AppCompatActivity {

    private TextView mTextMessage;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dryer);

        // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_dryer);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Instantiate all washers here.
        DryerClass D01 = new DryerClass();
        D01.notifImage = findViewById(R.id.Dryer1st_notif);
        D01.notifImage.setOnClickListener(D01.dryerOnClickListener);

        DryerClass D02 = new DryerClass();
        D02.notifImage = findViewById(R.id.Dryer2nd_notif);
        D02.notifImage.setOnClickListener(D02.dryerOnClickListener);

        DryerClass D03 = new DryerClass();
        D03.notifImage = findViewById(R.id.Dryer3rd_notif);
        D03.notifImage.setOnClickListener(D03.dryerOnClickListener);

        DryerClass D04 = new DryerClass();
        D04.notifImage = findViewById(R.id.Dryer4th_notif);
        D04.notifImage.setOnClickListener(D04.dryerOnClickListener);

        DryerClass D05 = new DryerClass();
        D05.notifImage = findViewById(R.id.Dryer5th_notif);
        D05.notifImage.setOnClickListener(D05.dryerOnClickListener);

        DryerClass D06 = new DryerClass();
        D06.notifImage = findViewById(R.id.Dryer6th_notif);
        D06.notifImage.setOnClickListener(D06.dryerOnClickListener);

        DryerClass D07 = new DryerClass();
        D07.notifImage = findViewById(R.id.Dryer7th_notif);
        D07.notifImage.setOnClickListener(D07.dryerOnClickListener);

        DryerClass D08 = new DryerClass();
        D08.notifImage = findViewById(R.id.Dryer8th_notif);
        D08.notifImage.setOnClickListener(D08.dryerOnClickListener);

        DryerClass D09 = new DryerClass();
        D09.notifImage = findViewById(R.id.Dryer9th_notif);
        D09.notifImage.setOnClickListener(D09.dryerOnClickListener);

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
            Intent intent = new Intent(Dryer.this, Login.class);
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
}
