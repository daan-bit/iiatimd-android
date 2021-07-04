package com.example.chauffeursapp;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;



import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityTimer extends AppCompatActivity implements View.OnClickListener {
    Random rand = new Random();
    int idRandom = rand.nextInt(100000);

    Button stopBtn;

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    String begin_shift;
    int u_id;
    int laatsteId;
    private boolean shiftBegonnen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        shiftBegonnen = false;
        Bundle bundle = getIntent().getExtras();
        u_id = bundle.getInt("user_id");

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Tijd: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        Werktijden[] werktijdens = new Werktijden[5];
        werktijdens[0] = new Werktijden(2, 1, "2021-06-30 11:55:00", "2021-06-30 19:55:00");

        //Database aanmaken
        AppDatabase db = AppDatabase.getInstance(getApplicationContext()); //Singelton gemaakt om er zo voor te zorgen dat er maar 1 db is ipv meer

        new Thread(new InsertWerktijdenTask(db, werktijdens[0])).start();
        try {

            Thread.sleep(500);
            Thread.currentThread().interrupt(); // very important
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //deze functie gebruiken we om te bepalen wanneer de gebruiker klaar is met zijn shift

       /* chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 30000) { // voor nu 30 seconden
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(ActivityTimer.this, "U bent klaar met rijden voor vandaag!", Toast.LENGTH_SHORT).show(); // chauffeur klaar met rijden
                    chronometer.stop();
                }
            }
        }); */

        stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(this);
    }
    public void startChronometer(View v) {

        /* We zetten gegevens in de database */
        if(!shiftBegonnen) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            Log.d("datum_nu", formatter.format(date));
            begin_shift = formatter.format((date));
            Werktijden[] werktijdens = new Werktijden[1];
            werktijdens[0] = new Werktijden(idRandom, u_id, formatter.format((date)), "null");
            AppDatabase db = AppDatabase.getInstance(getApplicationContext()); //Singelton gemaakt om er zo voor te zorgen dat er maar 1 db is ipv meer
            new Thread(new InsertWerktijdenTask(db, werktijdens[0])).start();
            try {
                Thread.sleep(500);
                Thread.currentThread().interrupt(); // very important
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /* Einde gegevens zetten in DB bij start */


            /* We halen de ID op van die insert */
            new Thread(new GetWerktijdenTask(db)).start();
            try {
                Thread.sleep(500);
                Thread.currentThread().interrupt(); // very important
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shiftBegonnen = true;
        }

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
        chronometer.stop();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (running) {
            if(shiftBegonnen) {
                chronometer.stop();
                pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                running = false;
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date date = new Date();
                String einde_shift = formatter.format((date));

                Werktijden[] werktijdens = new Werktijden[1];
                werktijdens[0] = new Werktijden(idRandom, u_id, begin_shift, einde_shift);
                AppDatabase db = AppDatabase.getInstance(getApplicationContext()); //Singelton gemaakt om er zo voor te zorgen dat er maar 1 db is ipv meer
                new Thread(new UpdateWerktijdenTask(db, werktijdens[0])).start();
                try {
                    Thread.sleep(500);
                    Thread.currentThread().interrupt(); // very important
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                shiftBegonnen = false;
            }

        }

    }

    @Override
    public void onClick(View v) {
        Intent toUserDashboard = new Intent(this, DashboardUserActivity.class);
        startActivity(toUserDashboard);
    }

}