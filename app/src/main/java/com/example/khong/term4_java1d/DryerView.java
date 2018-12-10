package com.example.khong.term4_java1d;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DryerView extends AppCompatActivity {

    private String userBlockChoice;

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
                    Intent intent = new Intent(DryerView.this, WasherView.class);
                    intent.putExtra("block_choice", userBlockChoice);
                    startActivity(intent);
                    DryerView.this.finish();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dryer_view);

        userBlockChoice = getIntent().getStringExtra("block_choice");

        RecyclerView rvDryerList = findViewById(R.id.rvDryerList);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_dryer);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child(userBlockChoice)
                .child("dryers");

        FirebaseRecyclerOptions<Machine> options =
                new FirebaseRecyclerOptions.Builder<Machine>()
                        .setQuery(query, Machine.class)
                        .build();

        FirebaseRecyclerAdapter firebaseAdapter = new FirebaseBlockDatabaseAdapter(options);

        firebaseAdapter.startListening();
        rvDryerList.setLayoutManager(new LinearLayoutManager(this));
        rvDryerList.setHasFixedSize(true);
        rvDryerList.setAdapter(firebaseAdapter);
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
            Intent intent = new Intent(DryerView.this, LogoutActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            return true;
        }

        if (id == R.id.ChooseBlock) {
            Intent intent = new Intent(DryerView.this, ChooseBlock.class);
            startActivity(intent);

            return true;
        }

        if (id == R.id.Statistics) {
            Intent intent = new Intent(DryerView.this, UserUsage.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

}
