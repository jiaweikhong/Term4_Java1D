package com.example.khong.term4_java1d;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sackcentury.shinebuttonlib.ShineButton;

public class MainActivity extends AppCompatActivity {

    ShineButton goToWashersImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToWashersImgBtn = (ShineButton) findViewById(R.id.po_image2);
        goToWashersImgBtn.init(this);
    }

}
