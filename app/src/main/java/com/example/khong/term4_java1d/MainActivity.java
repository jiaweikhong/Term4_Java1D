package com.example.khong.term4_java1d;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sackcentury.shinebuttonlib.ShineButton;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private CardView gotoWashers;
    private CardView gotoDryers;
    private ShineButton washersNotifAllImgBtn;
    private ShineButton dryersNotifAllImgBtn;
    private TextView washerNotifState;
    private TextView dryerNotifState;
    private boolean washerNotifStatus = false;
    private boolean dryerNotifStatus = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        washerNotifState = findViewById(R.id.washerNotifState);
        dryerNotifState = findViewById(R.id.dryerNotifState);

        gotoWashers = findViewById(R.id.goToWashers);
        gotoWashers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Washer.class);
                startActivity(intent);
            }
        });

        gotoDryers = findViewById(R.id.goToDryers);
        gotoDryers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dryer.class);
                startActivity(intent);
            }
        });

        washersNotifAllImgBtn = findViewById(R.id.washersNotifAllImgBtn);
        washersNotifAllImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(washersNotifAllImgBtn.isChecked()) {
                    Toast.makeText(MainActivity.this, "You will now be notified when next washer is available", Toast.LENGTH_LONG).show();
                    washerNotifState.setText(R.string.notification_enabled);
                }//TODO move to strings.xml
                //
                else{
                Toast.makeText(MainActivity.this, "You have remove notification for washer", Toast.LENGTH_LONG).show();
                    washerNotifState.setText(R.string.notification_disabled);
            }
            }
        });
        dryersNotifAllImgBtn = findViewById(R.id.dryersNotifAllImgBtn);
        if(dryerNotifStatus){
            dryerNotifState.setText(R.string.notification_unavailable);
            dryersNotifAllImgBtn.setBtnFillColor(R.color.colorPrimary);
            dryersNotifAllImgBtn.setShapeResource(R.drawable.ic_assets_disabledbell);
            dryersNotifAllImgBtn.setChecked(true);
        }
        dryersNotifAllImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dryerNotifStatus){
                    dryersNotifAllImgBtn.setChecked(true);
                    Toast.makeText(MainActivity.this, "There are available dryers", Toast.LENGTH_LONG).show();
                }
                if(dryersNotifAllImgBtn.isChecked()&&!dryerNotifStatus) {
                    Toast.makeText(MainActivity.this, "You will now be notified when next dryer is available", Toast.LENGTH_LONG).show();
                    dryerNotifState.setText(R.string.notification_enabled);
                }
                else if(!dryerNotifStatus){
                    Toast.makeText(MainActivity.this, "You have remove notification for dryer", Toast.LENGTH_LONG).show();
                    dryerNotifState.setText(R.string.notification_disabled);
                }
            }
        });

    }

}
