package com.example.chauffeursapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardAdminActivity extends AppCompatActivity implements View.OnClickListener{

    Button werknemerToevoegBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        werknemerToevoegBtn = (Button) findViewById(R.id.AddBtn);

        werknemerToevoegBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //Werknemer toevoegen
        Toast.makeText(DashboardAdminActivity.this, "Toevoegen", Toast.LENGTH_SHORT).show();
    }
}
