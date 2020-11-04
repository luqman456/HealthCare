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
    RelativeLayout sugarRelativeLayout;
    TextView textViewSugarAc,textViewSugarEac,textViewSugarLevel;
    String week = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthreport);
        textViewSugar = (TextView) findViewById(R.id.textViewSug);
        textViewBp = (TextView) findViewById(R.id.textViewBp);
        textViewSugarAc = (TextView) findViewById(R.id.textViewA1C);
        textViewSugarEac = (TextView) findViewById(R.id.textViewmmol);
        textViewSugarLevel = (TextView) findViewById(R.id.textViewSuagrLevel);
        sugarRelativeLayout = (RelativeLayout) findViewById(R.id.relativeSugar);
        context = this;
        databaseHelper = new DatabaseHelper(this);
        sugarList = new ArrayList<>();

//        Date c = Calendar.getInstance().getTime();
//        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        String formattedDate = df.format(c);
//        System.out.println("Current date => " + formattedDate);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        week = getIntent().getExtras().getString("week");

//        formattedDate,getCalculatedDate(formattedDate, "dd/MM/yyyy", -7)

        textViewSugar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                textViewBp.setBackgroundResource(R.drawable.roundedtextview);
                textViewSugar.setBackgroundResource(R.drawable.roundedcornertextviewcolor);
                sugarRelativeLayout.setVisibility(View.VISIBLE);

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

                // System.out.println("Current date => " + getCalculatedDate(formattedDate, "dd/MM/yyyy", -6));
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