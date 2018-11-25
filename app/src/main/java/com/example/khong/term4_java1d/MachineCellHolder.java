package com.example.khong.term4_java1d;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MachineCellHolder extends RecyclerView.ViewHolder {

    public TextView machineName;
    public TextView machineTimeData;
    public TextView machineTimeLabel;

    public ImageView machineStatus;
    public ImageButton machineNotify;

    public MachineCellHolder(final View cellView) {
        super(cellView);
        machineName = cellView.findViewById(R.id.machine_name);
        machineTimeData = cellView.findViewById(R.id.machine_timedata);
        machineTimeLabel = cellView.findViewById(R.id.machine_timelabel);

        machineStatus = cellView.findViewById(R.id.machine_icon);
    }


}
