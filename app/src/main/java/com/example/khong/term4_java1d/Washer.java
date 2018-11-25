package com.example.khong.term4_java1d;

import android.content.Intent;
import android.os.Bundle;
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

    private TextView mTextMessage;
    private ImageView Washer1st_notif;
    private Boolean Washer1st_notifstatus = false;
    private SwipeRefreshLayout refreshWasher;
    private ScrollView washerScrollView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washer);

        refreshWasher = findViewById(R.id.refreshWasher);
        washerScrollView = findViewById(R.id.washerScrollView);
        refreshWasher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //get firebase values
                refreshWasher.setRefreshing(false);
            }
        });
        // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_washer);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Instantiate all washers here.
        WasherClass W01 = new WasherClass();
        W01.notifImage = findViewById(R.id.Washer1st_notif);
        W01.notifImage.setOnClickListener(W01.washerOnClickListener);

        WasherClass W02 = new WasherClass();
        W02.notifImage = findViewById(R.id.Washer2nd_notif);
        W02.notifImage.setOnClickListener(W02.washerOnClickListener);

        WasherClass W03 = new WasherClass();
        W03.notifImage = findViewById(R.id.Washer3rd_notif);
        W03.notifImage.setOnClickListener(W03.washerOnClickListener);

        WasherClass W04 = new WasherClass();
        W04.notifImage = findViewById(R.id.Washer4th_notif);
        W04.notifImage.setOnClickListener(W04.washerOnClickListener);

        WasherClass W05 = new WasherClass();
        W05.notifImage = findViewById(R.id.Washer5th_notif);
        W05.notifImage.setOnClickListener(W05.washerOnClickListener);

        WasherClass W06 = new WasherClass();
        W06.notifImage = findViewById(R.id.Washer6th_notif);
        W06.notifImage.setOnClickListener(W06.washerOnClickListener);

        WasherClass W07 = new WasherClass();
        W07.notifImage = findViewById(R.id.Washer7th_notif);
        W07.notifImage.setOnClickListener(W07.washerOnClickListener);

        WasherClass W08 = new WasherClass();
        W08.notifImage = findViewById(R.id.Washer8th_notif);
        W08.notifImage.setOnClickListener(W08.washerOnClickListener);

        WasherClass W09 = new WasherClass();
        W09.notifImage = findViewById(R.id.Washer9th_notif);
        W09.notifImage.setOnClickListener(W09.washerOnClickListener);

        WasherClass W10 = new WasherClass();
        W10.notifImage = findViewById(R.id.Washer10th_notif);
        W10.notifImage.setOnClickListener(W10.washerOnClickListener);

        WasherClass W11 = new WasherClass();
        W11.notifImage = findViewById(R.id.Washer11th_notif);
        W11.notifImage.setOnClickListener(W11.washerOnClickListener);

        WasherClass W12 = new WasherClass();
        W12.notifImage = findViewById(R.id.Washer12th_notif);
        W12.notifImage.setOnClickListener(W12.washerOnClickListener);

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

}
