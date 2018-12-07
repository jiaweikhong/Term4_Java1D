package com.example.khong.term4_java1d;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;

public class FirebaseBlockDatabaseAdapter extends FirebaseRecyclerAdapter<Machine, MachineCellHolder> {

    FirebaseBlockDatabaseAdapter(FirebaseRecyclerOptions<Machine> options) {
        super(options);
    }

    @Override
    public MachineCellHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new instance of the ViewHolder, in this case we are using a custom
        // layout called R.layout.message for each item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_machine_cell_view, parent, false);

        return new MachineCellHolder(view);
    }

    @Override
    protected void onBindViewHolder(MachineCellHolder holder, int position, Machine machine) {
        holder.setMachineName(machine.getUuid());

        long unixTime = System.currentTimeMillis() / 1000L;
        long startTime = machine.getStartTime();
        long secondsElapsed = (unixTime - startTime);

        Log.d("FB/onBindViewHolder", machine.getTopicName());
        holder.setMachineTopic(machine.getTopicName());
        holder.setMachineTimeData(secondsElapsed);
        holder.setCollected(Boolean.parseBoolean(machine.getCollected()));

    }

    @Override
    public void onDataChanged() {
        // Called each time there is a new data snapshot. You may want to use this method
        // to hide a loading spinner or check for the "no documents" state and update your UI.
        Log.d("FB/onDataChanged", "Data changed");
    }

    @Override
    public void onError(DatabaseError e) {
        Log.e("FbBlockAdapter", e.toString());
    }

}
