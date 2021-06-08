package com.example.chauffeursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button toTimerScreenButton = findViewById(R.id.toTimerScreen);
        toTimerScreenButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        Bundle bundleForTimerScreen = new Bundle();
        String name = "Alex";
        Log.d("to timer", "went to timer");
        bundleForTimerScreen.putString("name", name);
        Intent toTimerScreenIntent = new Intent(this, ActivityTimer.class);
        startActivity(toTimerScreenIntent);
    }
}