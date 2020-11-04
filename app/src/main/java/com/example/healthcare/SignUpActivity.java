package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthcare.DB.DatabaseHelper;
import com.example.healthcare.Model.User;
import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity {
    EditText editTextName,editTextEmail,editTextAge,editTextHeight,editTextWeight,editTextPassword,editTextConfirmPassword;
    Button buttonSignUp;
    Context context;
    private DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initiateViews();
        context = this;

        databaseHelper = new DatabaseHelper(context);
        user = new User();


        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextName.getText().toString().isEmpty() || editTextEmail.getText().toString().isEmpty()
                || editTextAge.getText().toString().isEmpty() || editTextHeight.getText().toString().isEmpty()
                || editTextWeight.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()
                || editTextConfirmPassword.getText().toString().isEmpty())
                {
                    snackBar(v,"All fields are required");
                }
                else{

                    if(editTextPassword.getText().toString().equals(editTextConfirmPassword.getText().toString()))
                    {
                        createUserTable(v);
                    }
                    else{
                        snackBar(v,"Password and ConfirmPassword should be same");
                    }

                }
            }
        });
    }

    private void initiateViews() {
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextHeight = (EditText) findViewById(R.id.editTextHeight);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        buttonSignUp = (Button) findViewById(R.id.btnSignUp);
    }

    private void createUserTable(View v) {
        if(!databaseHelper.checkUser(editTextEmail.getText().toString().trim()))
        {
            user.setName(editTextName.getText().toString().trim());
            user.setEmail(editTextEmail.getText().toString().trim());
            user.setAge(Integer.parseInt(editTextAge.getText().toString().trim()));
            user.setHeight(editTextHeight.getText().toString().trim());
            user.setWeight(editTextWeight.getText().toString().trim());
            user.setPassword(editTextPassword.getText().toString().trim());
            databaseHelper.addUser(user);
            snackBar(v,"Registered Successfully");
        }
        else{
            snackBar(v,"User already exist against the email");
        }



    }

    private void snackBar(View v, String login_successfully){
        Snackbar.make(v, login_successfully, Snackbar.LENGTH_LONG).show();
    }
}