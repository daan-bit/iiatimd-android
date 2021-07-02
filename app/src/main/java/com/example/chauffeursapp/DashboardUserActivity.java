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
    Button holidayAddBtn;
    Button driveHistoryBtn;

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
        holidayAddBtn = (Button) findViewById(R.id.holidayAddBtn);
        holidayAddBtn.setOnClickListener(this);
        driveHistoryBtn = (Button) findViewById(R.id.holidayAddBtn);
        driveHistoryBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
           if (v == shiftStartBtn){
               toTimerScreen();
        }
           else if (v == holidayAddBtn) {
               toVakantieDagenScreen();
           }
           else if (v == driveHistoryBtn) {
               toHistoryScreen();
           }

    }



    public void toVakantieDagenScreen() {
        Bundle bundleForShift = new Bundle();
        Log.d("user_id_to_vakantie", String.valueOf(user_id));
        bundleForShift.putInt("user_id", user_id);
        Intent toVakantieDagen = new Intent(this, WerknemerVakantiedagenActivity.class);
        toVakantieDagen.putExtras(bundleForShift);
        startActivity(toVakantieDagen);
    }
    public void toTimerScreen() {
        Bundle bundleForShift = new Bundle();
        Log.d("user_id_to_timer", String.valueOf(user_id));
        Log.d("to timer", "went to timer");
        bundleForShift.putInt("user_id", user_id);
        Intent toUserDashboard = new Intent(this, ActivityTimer.class);
        toUserDashboard.putExtras(bundleForShift);
        startActivity(toUserDashboard);
    }

    public void toHistoryScreen() {
        Bundle bundleForShift = new Bundle();
        Log.d("user_id_to_history", String.valueOf(user_id));
        Log.d("to history", "went to timer");
        bundleForShift.putInt("user_id", user_id);
        Intent toHistoryDashboard = new Intent(this, GeschiedenisUserActivity.class);
        toHistoryDashboard.putExtras(bundleForShift);
        startActivity(toHistoryDashboard);
    }

}