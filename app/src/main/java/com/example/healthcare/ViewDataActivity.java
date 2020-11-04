package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcare.Adapter.BPAdapter;
import com.example.healthcare.Adapter.LinksListAdapter;
import com.example.healthcare.Adapter.SugarAdapter;
import com.example.healthcare.DB.DatabaseHelper;
import com.example.healthcare.Model.BPLevel;
import com.example.healthcare.Model.Sugar;
import com.example.healthcare.Model.UserData;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewDataActivity extends AppCompatActivity {
    TextView textViewSugar, textViewBp;
    ListView listViewSugar,listViewBp;
    SugarAdapter sugarAdapter;
    DatabaseHelper databaseHelper;
    BPAdapter bpAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        textViewSugar = (TextView) findViewById(R.id.textViewSug);
        textViewBp = (TextView) findViewById(R.id.textViewBp);
        listViewSugar = (ListView)findViewById(R.id.list_view_sugar);
        listViewBp = (ListView)findViewById(R.id.list_view_bp);

        databaseHelper = new DatabaseHelper(this);
        context = this;

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        textViewSugar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                textViewBp.setBackgroundResource(R.drawable.roundedtextview);
                textViewSugar.setBackgroundResource(R.drawable.roundedcornertextviewcolor);
                listViewSugar.setVisibility(View.VISIBLE);
                listViewBp.setVisibility(View.GONE);
                ArrayList<Sugar> dogList = databaseHelper.getSugarTableData(month,year);;
                sugarAdapter=new SugarAdapter((Activity) context, dogList);
                listViewSugar.setAdapter(sugarAdapter);

            }
        });

        textViewBp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                textViewBp.setBackgroundResource(R.drawable.roundedcornertextviewcolor);
                textViewSugar.setBackgroundResource(R.drawable.roundedtextview);
                listViewSugar.setVisibility(View.GONE);
                listViewBp.setVisibility(View.VISIBLE);
                ArrayList<BPLevel> dogList = databaseHelper.getBPTableData(month,year);;
                bpAdapter=new BPAdapter((Activity) context, dogList);
                listViewBp.setAdapter(bpAdapter);
            }
        });
    }
}