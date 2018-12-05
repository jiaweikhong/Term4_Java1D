package com.example.khong.term4_java1d;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

import com.example.khong.term4_java1d.ChartingFiles.ChartItem;
import com.example.khong.term4_java1d.ChartingFiles.LineChartItem;
import com.example.khong.term4_java1d.ChartingFiles.PieChartItem;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserUsage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_usage);

        //creation of listview statistics
        ListView lv = findViewById(R.id.listView1);
        ArrayList<ChartItem> list = new ArrayList<>();
        list.add(new PieChartItem(generateDataPie(), getApplicationContext()));
        list.add(new LineChartItem(generateDataLine(), getApplicationContext()));
        ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
        lv.setAdapter(cda);
    }

    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            //noinspection ConstantConditions
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            ChartItem ci = getItem(position);
            return ci != null ? ci.getItemType() : 0;
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }
    private PieData generateDataPie() {

        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Monday"));
        entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Thursday"));
        entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Sunday"));
        entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Saturday"));


        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        return new PieData(d);
    }
    private LineData generateDataLine() {

        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            values1.add(new Entry(i, (int) (Math.random() * 100) ));
        }

        LineDataSet d1 = new LineDataSet(values1, "Average volume " );
        d1.setLineWidth(3f);
        d1.setCircleRadius(7f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

        ArrayList<Entry> values2 = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            values2.add(new Entry(i, (int) (Math.random() * 100) ));
        }

        LineDataSet d2 = new LineDataSet(values2, "Your usage " );
        d2.setLineWidth(3f);
        d2.setCircleRadius(7f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        sets.add(d2);

        return new LineData(sets);
    }
}
