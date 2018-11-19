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
    private CardView gotoWashersImgBtn;
    private CardView gotoDryersImgBtn;
    private ShineButton washersNotifAllImgBtn;
    private ShineButton dryersNotifAllImgBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoWashersImgBtn = findViewById(R.id.goToWashers);
        gotoWashersImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Washer.class);
                startActivity(intent);
            }
        });

        gotoDryersImgBtn = findViewById(R.id.goToDryers);
        gotoDryersImgBtn.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(MainActivity.this, "You will now be notified when next washer is available", Toast.LENGTH_LONG).show();
                //TODO move to strings.xml
            }
        });
        dryersNotifAllImgBtn = findViewById(R.id.dryersNotifAllImgBtn);
        dryersNotifAllImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You will now be notified when next dryer is available", Toast.LENGTH_LONG).show();
            }
        });

    }

}
