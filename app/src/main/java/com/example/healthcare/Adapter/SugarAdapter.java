package com.example.healthcare.Adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.healthcare.Model.Sugar;
import com.example.healthcare.R;

import java.util.ArrayList;

public class SugarAdapter extends BaseAdapter {
    private  Activity context;
    private ArrayList<Sugar> sugarArrayList;

    public SugarAdapter(Activity context, ArrayList<Sugar> sugarArrayList) {
        this.context = context;
        this.sugarArrayList = sugarArrayList;
    }

    @Override
    public int getCount() {
        return sugarArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return sugarArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_sugar, null, true);

        Sugar sugar = sugarArrayList.get(position);

        TextView  sugar_level = (TextView) rowView.findViewById(R.id.sugar_level);
        TextView sugar_date = (TextView) rowView.findViewById(R.id.sugar_data);
        TextView sugar_month_year = (TextView) rowView.findViewById(R.id.sugar_month_year);
        TextView sugar_week = (TextView) rowView.findViewById(R.id.sugar_week);

        sugar_level.setText(String.valueOf("Sugar Level "+sugar.getSugar_level()));
        sugar_date.setText("Date "+sugar.getDate());
//        sugar_month_year.setText(""+sugar.getMonth_year());
//        sugar_week.setText(sugar.getWeek());

        return rowView;

    }
}
