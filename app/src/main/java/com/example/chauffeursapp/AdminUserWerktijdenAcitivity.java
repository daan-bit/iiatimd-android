package com.example.chauffeursapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminUserWerktijdenAcitivity extends AppCompatActivity {

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminuserwerktijden);
        userName = findViewById(R.id.naamUser);

        Bundle bundle = getIntent().getExtras();

        userName.setText("Welkom terug, " + bundle.getString("name", "Gebruiker"));
    }
}
