package com.example.chauffeursapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class WerknemerToevoegenActivity extends AppCompatActivity implements View.OnClickListener{

    EditText addEmail,addPass, addName, addPass2;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_werknemertoevoegen);

        addName = (EditText) findViewById(R.id.addNameField);
        addEmail = (EditText) findViewById(R.id.addEmailField);
        addPass = (EditText) findViewById(R.id.addPasswordField);
        addPass2 = (EditText) findViewById(R.id.addPassword2Field);
        addButton = (Button) findViewById(R.id.vakantieToevoegen);

        addButton.setOnClickListener(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }


    @Override
    public void onClick(View v) {
        if (addPass.getText().toString().equals(addPass2.getText().toString())) {
            APICalls.accountAanmaken(addEmail.getText().toString(), addPass.getText().toString(), addName.getText().toString(), this.getApplicationContext());

            NotificationCompat.Builder builder = new NotificationCompat.Builder(WerknemerToevoegenActivity.this, "My notification");
            builder.setContentTitle("Werknemer aangemaakt");
            builder.setContentText("Werknemer is aangemaakt en opgeslagen in database, de werknemer kan vanaf nu inloggen");
            builder.setSmallIcon(R.drawable.add_werknemer_foreground);
            builder.setAutoCancel(true);

            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(WerknemerToevoegenActivity.this);
            managerCompat.notify(1, builder.build());

            Intent toAdminDashboard = new Intent(this, DashboardAdminActivity.class);
            startActivity(toAdminDashboard);
        }else {
            Toast.makeText(WerknemerToevoegenActivity.this, "Wachtwoorden ongelijk", Toast.LENGTH_SHORT).show();
        }
    }



}

