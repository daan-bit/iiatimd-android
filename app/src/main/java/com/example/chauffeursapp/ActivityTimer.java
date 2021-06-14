package com.example.chauffeursapp;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;



import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityTimer extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Tijd: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000) { // voor nu 10 seconden
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(ActivityTimer.this, "U bent klaar met rijden voor vandaag!", Toast.LENGTH_SHORT).show(); // chauffeur klaar met rijden
                }
            }
        });
    }
    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
}