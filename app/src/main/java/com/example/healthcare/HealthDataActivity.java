package com.example.healthcare;

import android.app.DatePickerDialog;
import android.os.Bundle;

import com.example.healthcare.DB.DatabaseHelper;
import com.example.healthcare.Model.BPLevel;
import com.example.healthcare.Model.Sugar;
import com.example.healthcare.Model.UserData;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Calendar;

public class HealthDataActivity extends AppCompatActivity {
    MaterialSpinner spinner;
    Button btnSugar,btnsugarDate,btnbP,btnbPDate;
    EditText editTextSugar, editTextSystolic,editTextDiastolic,editTextSugarDate,editTextbPDate;
    LinearLayout linearLayoutSugar,linearLayoutbP;
    DatePickerDialog picker;
    DatabaseHelper databaseHelper;
    int no_of_day,no_of_months,no_of_year;
    String weeks = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_data);

        spinner = (MaterialSpinner) findViewById(R.id.spinner);
        editTextSugar = (EditText)findViewById(R.id.editTextSugar);
        editTextSystolic = (EditText)findViewById(R.id.editTextSystolic);
        editTextDiastolic = (EditText)findViewById(R.id.editTextDiastolic);
        editTextSugarDate = (EditText)findViewById(R.id.editsugarDate);
        editTextbPDate = (EditText)findViewById(R.id.editbPDate);
        btnSugar = (Button)findViewById(R.id.btnsugar);
        btnsugarDate = (Button)findViewById(R.id.btnsugarDate);
        btnbP = (Button)findViewById(R.id.btnbP);
        btnbPDate = (Button)findViewById(R.id.btnbpDate);
        linearLayoutSugar = (LinearLayout)findViewById(R.id.linearsugarLevel);
        linearLayoutbP = (LinearLayout)findViewById(R.id.linearbpLevel);

        databaseHelper = new DatabaseHelper(this);

        Calendar c = Calendar.getInstance();
        no_of_year = c.get(Calendar.YEAR);
        no_of_months = c.get(Calendar.MONTH);
        Log.e("no_of_year ",""+no_of_year);
        Log.e("no_of_month ",""+no_of_months);

        spinner.setItems("BP Level", "Sugar Level");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if(item.equals("BP Level"))
                {
                    linearLayoutSugar.setVisibility(View.GONE);
                    linearLayoutbP.setVisibility(View.VISIBLE);
                    editTextSystolic.setText("");
                    editTextDiastolic.setText("");
                    editTextbPDate.setText("");
                }
                else{
                    linearLayoutSugar.setVisibility(View.VISIBLE);
                    linearLayoutbP.setVisibility(View.GONE);
                    editTextSugar.setText("");
                    editTextSugarDate.setText("");
                }
                snackBar(view,item);
            }
        });

        btnSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserData.user_email.length()>0){

                    addSugarLevel(v,editTextSugar.getText().toString(),editTextSugarDate.getText().toString());
                }

            }
        });

        btnsugarDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        btnbP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserData.user_email.length()>0){
                    if(editTextSystolic.getText().toString().isEmpty() || editTextDiastolic.getText().toString().isEmpty() || editTextbPDate.getText().toString().isEmpty()){
                        snackBar(v,"All Fields Are Required");
                    }
                    else{
                        addBp(v,editTextSystolic.getText().toString(),editTextDiastolic.getText().toString(),editTextbPDate.getText().toString());
                    }
                }
            }
        });

        btnbPDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        editTextSugarDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });

        editTextSystolic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate();
            }
        });



    }

    private void addBp(View v,String systolic, String diastolic, String date) {
        if(databaseHelper.checkUserBP(UserData.user_email,date)){
            snackBar(v,"Already BP Level Exist Against This Date");
        }
        else{
            getWeeksNumber();
            BPLevel bpLevel = new BPLevel();
            bpLevel.setSystolic(Integer.parseInt(systolic));
            bpLevel.setDiastolic(Integer.parseInt(diastolic));
            bpLevel.setDate(date);
            if(databaseHelper.addUserBP(bpLevel,UserData.user_email,weeks,no_of_months,no_of_year) != -1){
                snackBar(v,"BP Level Add Successfully");
            }
            else{
                snackBar(v,"BP Level Not Add Successfully");
            }
        }
    }

    private void addSugarLevel(View v, String sugar_level, String date) {
        if(editTextSugar.getText().toString().isEmpty()){
            snackBar(v,"Please Enter Sugar Level");
        }

        else if(editTextSugarDate.getText().toString().isEmpty()){
            snackBar(v,"Please Select Date");
        }
        else{
            if(databaseHelper.checkUserSugar(UserData.user_email,date)){
                snackBar(v,"Already Sugar Level Exist Against This Date");
            }
            else{
                getWeeksNumber();
                Sugar sugar = new Sugar();
                sugar.setSugar_level(Integer.parseInt(sugar_level));
                sugar.setDate(date);
                if(databaseHelper.addUserSugar(sugar,UserData.user_email,weeks,no_of_months,no_of_year) != -1){
                    snackBar(v,"Sugar Level Add Successfully");
                }
                else{
                    snackBar(v,"Sugar Level Not Add Successfully");
                }
            }
        }
    }

    private void getWeeksNumber() {

        if((no_of_day>1 || no_of_day ==1) && no_of_day<7){
            weeks = "week_one";
        }
        else if(no_of_day == 7){
            weeks = "week_one";
        }
        else if(no_of_day >7 && no_of_day <14){
            weeks = "week_two";
        }
        else if(no_of_day == 14){
            weeks = "week_two";
        }
        else if(no_of_day>14 || no_of_day<21){
            weeks = "week_three";
        }
        else if(no_of_day == 21){
            weeks = "week_three";
        }
        else if(no_of_day>21 || no_of_day<28){
            weeks = "week_four";
        }
        else if(no_of_day == 28){
            weeks = "week_four";
        }

        else if(no_of_day >28){
            weeks = "week_five";
        }

    }

    private void showDate(){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        picker = new DatePickerDialog(HealthDataActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                no_of_day = dayOfMonth;
                no_of_months = monthOfYear;
                no_of_year = year;
                if(linearLayoutSugar.getVisibility() == View.VISIBLE){
                    editTextSugarDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
                else{
                    editTextbPDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }
        }, year, month, day);
        picker.show();
    }

    private void snackBar(View v, String login_successfully){
        Snackbar.make(v, login_successfully, Snackbar.LENGTH_LONG).show();
    }
}