package com.example.chauffeursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DashboardUserActivity extends AppCompatActivity implements View.OnClickListener {
    TextView welcomeUser;
    Button shiftStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboarduser);
        welcomeUser = findViewById(R.id.welkomUser);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");

        welcomeUser.setText("Welkom terug, " + bundle.getString("name", "Gebruiker"));


        shiftStartBtn = (Button) findViewById(R.id.shiftStartBtn);

        shiftStartBtn.setOnClickListener(this);



    }

    public void onClick(View v) {
        toTimerScreen();
    }


    public void toTimerScreen() {
        Intent toTimerScreenIntent = new Intent(this, ActivityTimer.class);
        startActivity(toTimerScreenIntent);
    }

}