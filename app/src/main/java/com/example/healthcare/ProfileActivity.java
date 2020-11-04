package com.example.healthcare;

import android.content.Context;
import android.os.Bundle;

import com.example.healthcare.DB.DatabaseHelper;
import com.example.healthcare.Model.User;
import com.example.healthcare.Progress.CustomProgressDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail, editTextAge, editTextHeight, editTextWeight, editTextPassword, editTextConfirmPassword;
    TextView textViewNameChar;
    Button buttonUpdate;
    Context context;
    private DatabaseHelper databaseHelper;
    User user;
    CustomProgressDialog mProgressDialog;
    String email = "";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initiateView();
        context = this;
        databaseHelper = new DatabaseHelper(context);
        user = new User();
        mProgressDialog = new CustomProgressDialog(context);

        if(getIntent().getExtras() != null){
            email = getIntent().getExtras().getString("email");
            if(email != ""){
                mProgressDialog.StartCircularPrograsBar("", "Please Wait...");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getUserProfile();
                    }
                }, 1000);
            }

        }

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextName.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty()
                        || editTextAge.getText().toString().isEmpty() || editTextHeight.getText().toString().isEmpty() || editTextWeight.getText().toString().isEmpty())
                {
                    snackBar(v,"All fields are required");
                }
                else{
                    User user = new User();
                    user.setName(editTextName.getText().toString().trim());
                    user.setEmail(editTextEmail.getText().toString().trim());
                    user.setAge(Integer.parseInt(editTextAge.getText().toString().trim()));
                    user.setHeight(editTextHeight.getText().toString().trim());
                    user.setWeight(editTextWeight.getText().toString().trim());


                    if(databaseHelper.updateUser(user)){
                        snackBar(v,"User Profile Update Successfully");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 1000);
                    }
                    else{
                        snackBar(v,"User Profile Not Update");
                    }
                }


            }
        });
    }

    private void getUserProfile() {
        user = databaseHelper.getAllUser(email);
        if(user != null){
            editTextName.append(user.getName());
            editTextName.setSelection(editTextName.getText().length());
            textViewNameChar.append(editTextName.getText().toString().substring(0,2).toUpperCase());
            editTextEmail.setText(user.getEmail());
            editTextAge.append(String.valueOf(user.getAge()));
            editTextHeight.append(user.getHeight());
            editTextWeight.append(user.getWeight());

            editTextName.requestFocus();
            editTextName.setSelection(editTextName.getText().length());

        }
        else{
            finish();
        }

        mProgressDialog.DismissPrograsBar();
    }

    private void initiateView() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextHeight = (EditText) findViewById(R.id.editTextHeight);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        textViewNameChar = (TextView)findViewById(R.id.textViewNameChar);
        buttonUpdate = (Button) findViewById(R.id.btnUpdate);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void snackBar(View v, String login_successfully){
        Snackbar.make(v, login_successfully, Snackbar.LENGTH_LONG).show();
    }
}