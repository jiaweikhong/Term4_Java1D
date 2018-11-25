package com.example.khong.term4_java1d;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class WasherView extends AppCompatActivity {

    private FirebaseController firebaseController;
    private String userBlockChoice;

    private RecyclerView rvWasherList;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dryer:
                    Intent intent = new Intent(WasherView.this, DryerView.class);
                    startActivity(intent);
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

        firebaseController = new FirebaseController();

        rvWasherList = findViewById(R.id.rvWasherList);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_washer);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        userBlockChoice = firebaseController.getUserBlockChoice();

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child(userBlockChoice)
                .child("washers");

        FirebaseRecyclerOptions<Machine> options =
                new FirebaseRecyclerOptions.Builder<Machine>()
                        .setQuery(query, Machine.class)
                        .build();

        FirebaseRecyclerAdapter firebaseAdapter = new FirebaseRecyclerAdapter<Machine, MachineCellHolder>(options) {
            @Override
            public MachineCellHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_machine_cell_view, parent, false);

                return new MachineCellHolder(view);
            }

            @Override
            protected void onBindViewHolder(MachineCellHolder holder, int position, Machine machine) {
                holder.machineName.setText(machine.getUuid());
                long unixTime = System.currentTimeMillis() / 1000L;
                long minutesElapsed = (unixTime - machine.getStartTime())/60;
                holder.machineTimeData.setText(Long.toString(minutesElapsed)+" mins");
                if (minutesElapsed < 45) {
                    holder.machineStatus.setImageResource(R.drawable.ic_assets_redcircle);
                } else if (minutesElapsed > 60) {
                    holder.machineStatus.setImageResource(R.drawable.ic_assets_greencircle);
                }

            }

            @Override
            public void onDataChanged() {
                // Called each time there is a new data snapshot. You may want to use this method
                // to hide a loading spinner or check for the "no documents" state and update your UI.
                // ...
            }

            @Override
            public void onError(DatabaseError e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        };

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

}
