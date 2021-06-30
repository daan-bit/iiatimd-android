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
    int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboarduser);
        welcomeUser = findViewById(R.id.welkomUser);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("user_id");
        Log.d("dit_is_user_id_begin", String.valueOf(id));
        user_id = id;

        welcomeUser.setText("Welkom terug, " + bundle.getString("name", "Gebruiker"));


        shiftStartBtn = (Button) findViewById(R.id.shiftStartBtn);

        shiftStartBtn.setOnClickListener(this);



    }

    public void onClick(View v) {
        toTimerScreen();
    }


    public void toTimerScreen() {
        Bundle bundleForShift = new Bundle();
        Log.d("user_id_to_timer", String.valueOf(user_id));
        Log.d("to timer", "went to timer");
        bundleForShift.putInt("user_id", user_id);
        Intent toUserDashboard = new Intent(this, ActivityTimer.class);
        toUserDashboard.putExtras(toUserDashboard);
        startActivity(toUserDashboard);
    }

}