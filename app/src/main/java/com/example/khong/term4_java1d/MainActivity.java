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

<<<<<<< HEAD
    ShineButton goToWashersImgBtn;
=======
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_washer:
                    //mTextMessage.setText(R.string.title_wa);
                    return true;
                case R.id.navigation_dryer:
                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
>>>>>>> parent of 4e5972f... removed nav bar from mainactivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        goToWashersImgBtn = (ShineButton) findViewById(R.id.po_image2);
        goToWashersImgBtn.init(this);
=======
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
>>>>>>> parent of 4e5972f... removed nav bar from mainactivity
    }

}
