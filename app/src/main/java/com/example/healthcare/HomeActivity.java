package com.example.healthcare;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.healthcare.Model.UserData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    CardView cardViewProfile, cardViewHealthLink, cardViewHealthData, cardViewHealthReport,cardViewShowData;
    TextView textViewNameChar, textViewEmail;
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initiateViews();

        if (getIntent().getExtras() != null) {
            email = getIntent().getExtras().getString("email");
            textViewNameChar.setText(getIntent().getExtras().getString("name").substring(0, 2).toUpperCase());
            textViewEmail.setText(getIntent().getExtras().getString("email"));
            UserData.user_email = textViewEmail.getText().toString();
        }

        cardViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        cardViewHealthLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HealthLinkActivity.class);
                startActivity(intent);
            }
        });

        cardViewHealthData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HealthDataActivity.class);
                startActivity(intent);
            }
        });

        cardViewHealthReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }

        });

        cardViewShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ViewDataActivity.class);
                startActivity(intent);
            }
        });

    }

    private void showDialog() {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.customview);

        TextView week_one = (TextView) dialog.findViewById(R.id.textViewAFirstWeek);
        TextView week_two = (TextView) dialog.findViewById(R.id.textViewASecondWeek);
        TextView week_three = (TextView) dialog.findViewById(R.id.textViewAThirdWeek);
        TextView week_four = (TextView) dialog.findViewById(R.id.textViewAFourtWeek);
        TextView week_five = (TextView) dialog.findViewById(R.id.textViewALasttWeek);

        week_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HealthreportActivity.class);
                intent.putExtra("week","week_one");
                startActivity(intent);
                dialog.dismiss();
            }
        });

        week_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HealthreportActivity.class);
                intent.putExtra("week","week_two");
                startActivity(intent);
                dialog.dismiss();
            }
        });

        week_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HealthreportActivity.class);
                intent.putExtra("week","week_three");
                startActivity(intent);
                dialog.dismiss();
            }
        });
        week_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HealthreportActivity.class);
                intent.putExtra("week","week_four");
                startActivity(intent);
                dialog.dismiss();
            }
        });

        week_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HealthreportActivity.class);
                intent.putExtra("week","week_five");
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void initiateViews() {
        cardViewProfile = (CardView) findViewById(R.id.cardViewProfile);
        cardViewHealthLink = (CardView) findViewById(R.id.cardViewHealthLink);
        cardViewHealthData = (CardView) findViewById(R.id.cardViewHealthtData);
        cardViewHealthReport = (CardView) findViewById(R.id.cardViewHealthReport);
        cardViewShowData = (CardView) findViewById(R.id.cardViewViewData);
        textViewNameChar = (TextView) findViewById(R.id.textViewNameChar);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
    }
}