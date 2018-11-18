package com.example.khong.term4_java1d;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Notifications extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dryer:
                    // mTextMessage.setText(R.string.title_home);
                    Intent intentToDryer = new Intent(Notifications.this, Dryer.class);
                    startActivity(intentToDryer);
                    return true;
                case R.id.navigation_washer:
                    // mTextMessage.setText(R.string.title_dashboard);
                    Intent intentToWasher = new Intent(Notifications.this, Washer.class);
                    startActivity(intentToWasher);
                    break;
                case R.id.navigation_main:
                    Intent intentToMain = new Intent(Notifications.this, MainActivity.class);
                    startActivity(intentToMain);
                    break;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_notifications);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }
}
