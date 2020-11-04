package com.example.healthcare.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.healthcare.Model.BPLevel;
import com.example.healthcare.R;

import java.util.ArrayList;

public class BPAdapter extends BaseAdapter {
    private Activity context;
    private ArrayList<BPLevel> bpLevelArrayList;

    public BPAdapter(Activity context, ArrayList<BPLevel> bpLevelArrayList) {
        this.context = context;
        this.bpLevelArrayList = bpLevelArrayList;
    }

    @Override
    public int getCount() {
        return bpLevelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return bpLevelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.bp_list, null, true);

        BPLevel bpLevel = bpLevelArrayList.get(position);

        TextView bp_systolic = (TextView) rowView.findViewById(R.id.bp_systolic);
        TextView bp_diastolic = (TextView) rowView.findViewById(R.id.bp_diastolic);
        TextView bp_date = (TextView) rowView.findViewById(R.id.bp_data);


        bp_systolic.setText(String.valueOf("Systolic Level "+bpLevel.getSystolic()));
        bp_diastolic.setText(String.valueOf("Diastolic Level "+bpLevel.getDiastolic()));
        bp_date.setText("Date "+bpLevel.getDate());
        return rowView;
    }
}
