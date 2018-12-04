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


public class WasherView extends AppCompatActivity {

    private String userBlockChoice;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dryer:
                    Intent intent = new Intent(WasherView.this, DryerView.class);
                    intent.putExtra("block_choice", userBlockChoice);
                    startActivity(intent);
                    WasherView.this.finish();
                    break;
                case R.id.navigation_washer:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washer_view);

        RecyclerView rvWasherList = findViewById(R.id.rvWasherList);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_washer);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        userBlockChoice = getIntent().getStringExtra("block_choice");

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child(userBlockChoice)
                .child("washers");

        FirebaseRecyclerOptions<Machine> options =
                new FirebaseRecyclerOptions.Builder<Machine>()
                        .setQuery(query, Machine.class)
                        .build();

        FirebaseRecyclerAdapter firebaseAdapter = new FirebaseBlockDatabaseAdapter(options);

        firebaseAdapter.startListening();
        rvWasherList.setLayoutManager(new LinearLayoutManager(this));
        rvWasherList.setHasFixedSize(true);
        rvWasherList.setAdapter(firebaseAdapter);

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
            Intent intent = new Intent(WasherView.this, LogoutActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

            return true;
        }

        if (id == R.id.ChooseBlock) {
            Intent intent = new Intent(WasherView.this, ChooseBlock.class);
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
