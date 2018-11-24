package com.example.khong.term4_java1d;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CardView gotoWashers;
    private CardView gotoDryers;
    private CustomShineButton washersNotifAllImgBtn;
    private CustomShineButton dryersNotifAllImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random randomno = new Random();
        // get next next boolean value

        washersNotifAllImgBtn =(CustomShineButton)findViewById(R.id.washersNotifAllImgBtn);
        dryersNotifAllImgBtn =(CustomShineButton) findViewById(R.id.dryersNotifAllImgBtn);
        dryersNotifAllImgBtn.NotifStatus = randomno.nextBoolean();
        washersNotifAllImgBtn.NotifStatus = randomno.nextBoolean();//set to random, it should be firebase

        washersNotifAllImgBtn.NotifState = findViewById(R.id.washerNotifState);
        dryersNotifAllImgBtn.NotifState = findViewById(R.id.dryerNotifState);

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


        washersNotifAllImgBtn.setUnavailable();
        dryersNotifAllImgBtn.setUnavailable();

        washersNotifAllImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                washersNotifAllImgBtn.WasherOnClickFunction();
            }
        });



        dryersNotifAllImgBtn.setOnClickListener(new View.OnClickListener() {    
            @Override
            public void onClick(View v) {
                dryersNotifAllImgBtn.DryerOnClickFunction();

            }
        });

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
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.ChooseBlock) {
            Intent intent = new Intent(MainActivity.this, ChooseBlock.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
