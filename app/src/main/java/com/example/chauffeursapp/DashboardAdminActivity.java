package com.example.chauffeursapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardAdminActivity extends AppCompatActivity implements View.OnClickListener{

    Button werknemerToevoegBtn;
    Button geschiedenisBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardadmin);

        werknemerToevoegBtn = (Button) findViewById(R.id.AddBtn);
        geschiedenisBtn = (Button) findViewById(R.id.historyBtn);

        werknemerToevoegBtn.setOnClickListener(this);
        geschiedenisBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == werknemerToevoegBtn){
            Intent toWerknemerToevoegen = new Intent(this, WerknemerToevoegenActivity.class);
            startActivity(toWerknemerToevoegen);
        }

        else if (v == geschiedenisBtn){
            // Geschiedenis werknemers bekijken
            Toast.makeText(DashboardAdminActivity.this, "Geschiedenis werknemers", Toast.LENGTH_SHORT).show();
        }
    }
}
