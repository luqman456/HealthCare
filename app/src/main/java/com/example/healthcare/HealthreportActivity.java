package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcare.DB.DatabaseHelper;
import com.example.healthcare.Model.UserData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HealthreportActivity extends AppCompatActivity {
    TextView textViewSugar, textViewBp;
    private Context context;
    DatabaseHelper databaseHelper;
    List<Integer> sugarList;
    RelativeLayout sugarRelativeLayout,BpRelativeLayout;
    TextView textViewSugarAc,textViewSugarEac,textViewSugarLevel;
    TextView textViewBpLevel,textViewSugarSystolic,textViewDiastolic;
    String week = "";
    List<Integer> bpList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthreport);
        textViewSugar = (TextView) findViewById(R.id.textViewSug);
        textViewBp = (TextView) findViewById(R.id.textViewBp);
        textViewSugarAc = (TextView) findViewById(R.id.textViewA1C);
        textViewSugarEac = (TextView) findViewById(R.id.textViewmmol);
        textViewSugarLevel = (TextView) findViewById(R.id.textViewSuagrLevel);
        textViewBpLevel = (TextView) findViewById(R.id.textViewPBLevel);
        textViewSugarSystolic = (TextView) findViewById(R.id.textViewsystolic);
        textViewDiastolic = (TextView) findViewById(R.id.textViewDiastolic);
        sugarRelativeLayout = (RelativeLayout) findViewById(R.id.relativeSugar);
        BpRelativeLayout = (RelativeLayout) findViewById(R.id.relativeBP);
        context = this;
        databaseHelper = new DatabaseHelper(this);
        sugarList = new ArrayList<>();
        bpList = new ArrayList<>();

//        Date c = Calendar.getInstance().getTime();
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        String formattedDate = df.format(c);
//        System.out.println("Current date => " + formattedDate);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        week = getIntent().getExtras().getString("week");


        textViewSugar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                textViewBp.setBackgroundResource(R.drawable.roundedtextview);
                textViewSugar.setBackgroundResource(R.drawable.roundedcornertextviewcolor);
                sugarRelativeLayout.setVisibility(View.VISIBLE);
                BpRelativeLayout.setVisibility(View.GONE);

                textViewSugarAc.setText("0.0 %");
                textViewSugarEac.setText("0.0 mmol/l");

                sugarList.clear();
                int suger_level = 0;
                int suger_count = 0;
                int sugar_result = 0;
                sugarList = databaseHelper.getSugar(UserData.user_email, week, month, year);
                Log.e("list_count", "" + sugarList.size());
                for (int i = 0; i <sugarList.size(); i++) {
                    suger_count++;
                    suger_level = suger_level + sugarList.get(i);
                    Log.e("Sugar", "" + sugarList.get(i));
                }
                Log.e("counts", "" + suger_count);
                if (suger_count > 0) {
                    Log.e("calculated_sugar level ", "" + (sugar_result = suger_level / suger_count));
                    if(suger_count ==1){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(suger_count == 2){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(suger_count == 3){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(suger_count == 4){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(suger_count==5){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(suger_count==6){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(suger_count == 7){

                        if(sugar_result >= 126 && sugar_result <= 140){
                            textViewSugarAc.setText("6 %");
                            textViewSugarEac.setText("7.0 mmol/l");
                        }
                        else if(sugar_result >= 140  && sugar_result <=154){
                            textViewSugarAc.setText("6.5 %");
                            textViewSugarEac.setText("7.8 mmol/l");
                        }
                        else if(sugar_result >=154 && sugar_result<=169){
                            textViewSugarAc.setText("7 %");
                            textViewSugarEac.setText("8.6 mmol/l");
                        }
                        else if(sugar_result >= 169 && sugar_result<=183){
                            textViewSugarAc.setText("7.5 %");
                            textViewSugarEac.setText("9.4 mmol/l");
                        }
                        else if(sugar_result >=183 && sugar_result<=197){
                            textViewSugarAc.setText("8 %");
                            textViewSugarEac.setText("10.1 mmol/l");
                        }
                        else if(sugar_result >=197 && sugar_result<=212){
                            textViewSugarAc.setText("8.5 %");
                            textViewSugarEac.setText("10.9 mmol/l");
                        }else if(sugar_result >=212 && sugar_result<=226){
                            textViewSugarAc.setText("9 %");
                            textViewSugarEac.setText("11.8 mmol/l");
                        }else if(sugar_result >=226 && sugar_result<=240){
                            textViewSugarAc.setText("9.5 %");
                            textViewSugarEac.setText("12.6 mmol/l");
                        }
                        else if(sugar_result >=240){
                            textViewSugarAc.setText("10 %");
                            textViewSugarEac.setText("13.4 mmol/l");
                        }
                        else if(sugar_result >=240 && sugar_result<=380){
                            textViewSugarAc.setText("10 %");
                            textViewSugarEac.setText("13.4 mmol/l");
                        }

                        if(sugar_result<50){
                            textViewSugarLevel.setText("Sugar Level To Low Please enter correct data");
                            textViewSugarLevel.setBackgroundResource(R.drawable.nolevel);
                        }
                        else if(sugar_result >=50 && sugar_result <=80){
                            textViewSugarLevel.setText("Sugar Level Low");
                            textViewSugarLevel.setBackgroundResource(R.drawable.low);
                        }
                        else if(sugar_result >=80 && sugar_result <=115){
                            textViewSugarLevel.setText("Sugar Level Normal");
                            textViewSugarLevel.setBackgroundResource(R.drawable.normal);
                        }

                        else if(sugar_result >=115 && sugar_result <=150){
                            textViewSugarLevel.setText("Sugar Level Normal");
                            textViewSugarLevel.setBackgroundResource(R.drawable.normal);
                        }

                        else if(sugar_result>= 150 && sugar_result<=380){
                            textViewSugarLevel.setText("Sugar Level Diabetes");
                            textViewSugarLevel.setBackgroundResource(R.drawable.high);
                        }
                        else if(sugar_result>380){
                            textViewSugarLevel.setText("Please enter correct data");
                            textViewSugarLevel.setBackgroundResource(R.drawable.nolevel);
                        }

                    }
                }

                else{
                    sugarRelativeLayout.setVisibility(View.GONE);
                    Toast.makeText(context, "Please add data", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textViewBp.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                textViewBp.setBackgroundResource(R.drawable.roundedcornertextviewcolor);
                textViewSugar.setBackgroundResource(R.drawable.roundedtextview);
                sugarRelativeLayout.setVisibility(View.GONE);
                BpRelativeLayout.setVisibility(View.VISIBLE);

                textViewSugarSystolic.setText("0");
                textViewDiastolic.setText("0");

                bpList.clear();
                int systolic_level = 0;
                int pb_count = 0;
                int systolic_result = 0;
                int diastolic_result = 0;
                int diastolic_level = 0;
                bpList = databaseHelper.getBP(UserData.user_email, week, month, year);
                Log.e("list_count", "" + bpList.size());
                for (int i = 0; i <bpList.size(); i++) {
                    pb_count++;
                    systolic_level = systolic_level + bpList.get(i);
                    ++i;
                    diastolic_level = diastolic_level + bpList.get(i);
                }
                Log.e("counts", "" + pb_count);
                Log.e("systolic", "" + systolic_level);
                Log.e("dystolic", "" + diastolic_level);

                if (pb_count > 0) {
                    systolic_result = systolic_level/pb_count;
                    diastolic_result = diastolic_level/pb_count;
                    Log.e("systolic_result", "" + systolic_result);
                    Log.e("diastolic_result", "" + diastolic_result);
                    if(pb_count ==1){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(pb_count == 2){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(pb_count == 3){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(pb_count == 4){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(pb_count ==5){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(pb_count ==6){
                        Toast.makeText(context, "Please Add Remaining Days Data", Toast.LENGTH_SHORT).show();
                    }
                    else if(pb_count == 7)
                    {

                        if(systolic_result >= 120 && diastolic_result <= 180){

                            textViewSugarSystolic.setText(String.valueOf(systolic_result));
                            textViewDiastolic.setText(String.valueOf(diastolic_result));

                            textViewBpLevel.setText("BP Level Normal");
                            textViewBpLevel.setBackgroundResource(R.drawable.normal);
                        }
                        else if((systolic_result >= 120 && systolic_result<=129)  && diastolic_result <=80){

                            textViewSugarSystolic.setText(String.valueOf(systolic_result));
                            textViewDiastolic.setText(String.valueOf(diastolic_result));

                            textViewBpLevel.setText("BP Level Elevated");
                            textViewBpLevel.setBackgroundResource(R.drawable.elevated);
                        }
                        else if((systolic_result >= 130 && systolic_result<=139)  && (diastolic_result >=80 && diastolic_result<=89)){

                            textViewSugarSystolic.setText(String.valueOf(systolic_result));
                            textViewDiastolic.setText(String.valueOf(diastolic_result));

                            textViewBpLevel.setText("BP Level High Stage 1");
                            textViewBpLevel.setBackgroundResource(R.drawable.stage1);

                        }
                        else if((systolic_result >= 140)  && (diastolic_result >=90)){

                            textViewSugarSystolic.setText(String.valueOf(systolic_result));
                            textViewDiastolic.setText(String.valueOf(diastolic_result));

                            textViewBpLevel.setText("BP Level High Stage 2");
                            textViewBpLevel.setBackgroundResource(R.drawable.stage2);
                        }
                        else if((systolic_result >= 180)  && (diastolic_result >=120)){

                            textViewSugarSystolic.setText(String.valueOf(systolic_result));
                            textViewDiastolic.setText(String.valueOf(diastolic_result));

                            textViewBpLevel.setText("BP Level Hypertensive Crisis");
                            textViewBpLevel.setBackgroundResource(R.drawable.hypertensive);
                        }
                        else if(systolic_result >=197 && systolic_result <=212){

                            textViewSugarSystolic.setText(String.valueOf(systolic_result));
                            textViewDiastolic.setText(String.valueOf(diastolic_result));

                        }else if(systolic_result >=212 && systolic_result <=226){

                            textViewSugarSystolic.setText(String.valueOf(systolic_result));
                            textViewDiastolic.setText(String.valueOf(diastolic_result));

                        }else if(systolic_result >=226 && systolic_result <=240){

                            textViewSugarSystolic.setText(String.valueOf(systolic_result));
                            textViewDiastolic.setText(String.valueOf(diastolic_result));
                        }
                        else if(systolic_result >=240){

                            textViewSugarSystolic.setText(String.valueOf(systolic_result));
                            textViewDiastolic.setText(String.valueOf(diastolic_result));
                        }
                        else if(systolic_result >=240 && systolic_result <=380){

                            textViewSugarSystolic.setText(String.valueOf(systolic_result));
                            textViewDiastolic.setText(String.valueOf(diastolic_result));
                        }

                    }
                }
                else{
                    BpRelativeLayout.setVisibility(View.GONE);
                    Toast.makeText(context, "Please add data", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public static String getCalculatedDate(String date, String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        if (!date.isEmpty()) {
            try {
                cal.setTime(s.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }

}