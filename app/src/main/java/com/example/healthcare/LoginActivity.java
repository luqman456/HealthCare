package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthcare.DB.DatabaseHelper;
import com.example.healthcare.Model.User;
import com.example.healthcare.Progress.CustomProgressDialog;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;
    Context context;
    private DatabaseHelper databaseHelper;
    CustomProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initiateView();
        context = this;
        databaseHelper = new DatabaseHelper(context);
        mProgressDialog = new CustomProgressDialog(context);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser(v);
            }
        });


    }

    private void checkUser(View v) {
        if(editTextEmail.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()){
            snackBar(v,"Email and Password required");
        }
        else{
            mProgressDialog.StartCircularPrograsBar("", "Please Wait...");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if(databaseHelper.checkUser(editTextEmail.getText().toString().trim(),editTextPassword.getText().toString().trim()))
                    {
                        User user = databaseHelper.getData(editTextEmail.getText().toString().trim(),editTextPassword.getText().toString().trim());
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("name",user.getName());
                        intent.putExtra("email",user.getEmail());
                        startActivity(intent);
                        mProgressDialog.DismissPrograsBar();
                        snackBar(v,"Login Successfully");
                    }
                    else{
                        mProgressDialog.DismissPrograsBar();
                        snackBar(v,"Not Login Successfully");
                    }
                }
            }, 1000);


        }
    }

    private void initiateView() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
    }

    private void snackBar(View v, String login_successfully){
        Snackbar.make(v, login_successfully, Snackbar.LENGTH_LONG).show();
    }
}