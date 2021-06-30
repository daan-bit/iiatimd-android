package com.example.chauffeursapp;
import android.content.Context;
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
        Werktijden[] werktijdens = new Werktijden[1];
        werktijdens[0] = new Werktijden(1, 1, "2021-06-30 11:55:00", "6", "1", "2021-06-30 19:55:00");

        //Database aanmaken
        AppDatabase db = AppDatabase.getInstance(getApplicationContext()); //Singelton gemaakt om er zo voor te zorgen dat er maar 1 db is ipv meer

        new Thread(new InsertWerktijdenTask(db, werktijdens[0])).start();
        try {
            Thread.sleep(500);
            Thread.currentThread().interrupt(); // very important
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
               /* if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000) { // voor nu 10 seconden
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(ActivityTimer.this, "U bent klaar met rijden voor vandaag!", Toast.LENGTH_SHORT).show(); // chauffeur klaar met rijden
                }*/
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

    @Override
    public void onStop() {
        super.onStop();
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }

    }
}