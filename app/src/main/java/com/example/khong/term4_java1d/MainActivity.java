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

    private TextView mTextMessage;
    private CardView gotoWashers;
    private CardView gotoDryers;
    private CustomShineButton washersNotifAllImgBtn;
    private CustomShineButton dryersNotifAllImgBtn;
    private TextView washerNotifState;
    private TextView dryerNotifState;
    private boolean washerNotifStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random randomno = new Random();

        // get next next boolean value 
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

        washersNotifAllImgBtn =(CustomShineButton)findViewById(R.id.washersNotifAllImgBtn);

        washersNotifAllImgBtn.NotifStatus = randomno.nextBoolean();//set to random, it should be firebase      
if(washersNotifAllImgBtn.NotifStatus){
            washerNotifState.setText(R.string.notification_unavailable);
            washersNotifAllImgBtn.setBtnFillColor(0xFFA0A0A0);
            washersNotifAllImgBtn.setShapeResource(R.drawable.ic_assets_disabledbell);
            washersNotifAllImgBtn.setChecked(true);
        }

        washersNotifAllImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(washersNotifAllImgBtn.NotifStatus){
                    washersNotifAllImgBtn.setChecked(true);
                    Toast.makeText(MainActivity.this, "There are available washers", Toast.LENGTH_LONG).show();
                }
                if(washersNotifAllImgBtn.isChecked()&&!washersNotifAllImgBtn.NotifStatus) {
                    Toast.makeText(MainActivity.this, "You will now be notified when next washer is available", Toast.LENGTH_LONG).show();
                    washerNotifState.setText(R.string.notification_enabled);
                }//TODO move to strings.xml
                //
                else if(!washersNotifAllImgBtn.NotifStatus) {
                Toast.makeText(MainActivity.this, "You have remove notification for washer", Toast.LENGTH_LONG).show();
                    washerNotifState.setText(R.string.notification_disabled);
            }
            }
        });
        dryersNotifAllImgBtn =(CustomShineButton) findViewById(R.id.dryersNotifAllImgBtn);

        dryersNotifAllImgBtn.NotifStatus = randomno.nextBoolean();
        if(dryersNotifAllImgBtn.NotifStatus){
            dryerNotifState.setText(R.string.notification_unavailable);
            dryersNotifAllImgBtn.setBtnFillColor(0xFFA0A0A0);
            dryersNotifAllImgBtn.setShapeResource(R.drawable.ic_assets_disabledbell);
            dryersNotifAllImgBtn.setChecked(true);
        }
        dryersNotifAllImgBtn.setOnClickListener(new View.OnClickListener() {    
            @Override
            public void onClick(View v) {
                if(dryersNotifAllImgBtn.NotifStatus){
                    dryersNotifAllImgBtn.setChecked(true);
                    Toast.makeText(MainActivity.this, "There are available dryers", Toast.LENGTH_LONG).show();
                }
                if(dryersNotifAllImgBtn.isChecked()&&!dryersNotifAllImgBtn.NotifStatus) {
                    Toast.makeText(MainActivity.this, "You will now be notified when next dryer is available", Toast.LENGTH_LONG).show();
                    dryerNotifState.setText(R.string.notification_enabled);
                }
                else if(!dryersNotifAllImgBtn.NotifStatus){
                    Toast.makeText(MainActivity.this, "You have remove notification for dryer", Toast.LENGTH_LONG).show();
                    dryerNotifState.setText(R.string.notification_disabled);
                }
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
