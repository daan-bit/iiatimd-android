package com.example.chauffeursapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardUserActivity extends AppCompatActivity implements View.OnClickListener {
    TextView welcomeUser;
    Button shiftStartBtn;
    Button holidayAddBtn;
    Button sendDataBtn;


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
        sendDataBtn = (Button) findViewById(R.id.sendDataBtn);
        sendDataBtn.setOnClickListener(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    public void onClick(View v) {
           if (v == shiftStartBtn){
               toTimerScreen();
        }
           else if (v == holidayAddBtn) {
               toVakantieDagenScreen();
           }

           else if( v== sendDataBtn) {

               Toast.makeText(this.getApplicationContext(), "Ongeblik geduld aub, het kan enkele seconden duren...", Toast.LENGTH_SHORT).show();
               AppDatabase db = AppDatabase.getInstance(getApplicationContext()); //Singelton gemaakt om er zo voor te zorgen dat er maar 1 db is ipv meer
               
               new Thread(new InsertWerktijdenLaravelTask(db, this.getApplicationContext())).start();
               APICalls.getAllWerktijden(this.getApplicationContext());

               new Thread(new InsertVakantiedagenLaravelTask(db, this.getApplicationContext())).start();
               APICalls.getAllVakantiedagen(this.getApplicationContext());

               NotificationCompat.Builder builder = new NotificationCompat.Builder(DashboardUserActivity.this, "My notification");
               builder.setContentTitle("Gegevens gerefresht");
               builder.setContentText("Alle gegevens zijn verstuurd en opgehaald vanuit de API");
               builder.setSmallIcon(R.drawable.ic_launcher_background);
               builder.setAutoCancel(true);

               NotificationManagerCompat managerCompat = NotificationManagerCompat.from(DashboardUserActivity.this);
               managerCompat.notify(1, builder.build());
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

}