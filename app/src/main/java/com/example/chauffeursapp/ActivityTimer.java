package com.example.chauffeursapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityTimer extends AppCompatActivity {

    TextView timerText;
    Button stopStartButton;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0; //the start of time


    boolean timerStarted = false;
    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.activity_timer);

        timerText = (TextView) findViewById(R.id.timerText);
        stopStartButton = (Button) findViewById(R.id.startStopButton);

        timer = new Timer();
    }

    public void startStopTapped(View view) {

        if(timerStarted == false) {
            timerStarted = true;
            setButtonUI("STOP", R.color.red);

            startTimer();
        } else {
            timerStarted = false;
            setButtonUI("START", R.color.green);
            timerTask.cancel();
        }
    }

    private void setButtonUI(String start, int color) {
        stopStartButton.setText(start);
        stopStartButton.setTextColor(ContextCompat.getColor(this, color));
    }

    private void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }


    public void resetTapped(View view) {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Start opnieuw");
        resetAlert.setMessage("Per ongeluk op start gedrukt? Start de tijd opnieuw, zodat u nu aan de slag kunt met het rijden!");
        resetAlert.setPositiveButton("Herstart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(timerTask != null) {
                    timerTask.cancel();
                    setButtonUI("START", R.color.green);
                    time = 0.0;
                    timerStarted = false;
                    timerText.setText(formatTime(0, 0, 0));
                }
            }
        });

        resetAlert.setNeutralButton("Annuleer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        resetAlert.show();
    }


    private String getTimerText() {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;

        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);

    }

    private String formatTime(int seconds, int minutes, int hours) {
        return String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }
}
