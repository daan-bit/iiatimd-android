package com.example.chauffeursapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardAdminActivity extends AppCompatActivity implements View.OnClickListener{

    Button werknemerToevoegBtn;
    Button geschiedenisBtn;
    Button vakantieWerknemersBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboardadmin);

        werknemerToevoegBtn = (Button) findViewById(R.id.shiftStartBtn);
        geschiedenisBtn = (Button) findViewById(R.id.geschiedenisGebruikerBtn);
        vakantieWerknemersBtn = (Button) findViewById(R.id.werknemerVakantieBtn);

        werknemerToevoegBtn.setOnClickListener(this);
        geschiedenisBtn.setOnClickListener(this);
        vakantieWerknemersBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == werknemerToevoegBtn){
            Intent toWerknemerToevoegen = new Intent(this, WerknemerToevoegenActivity.class);
            startActivity(toWerknemerToevoegen);
        }

        else if (v == geschiedenisBtn){
            Intent toGeschiedenisAdmin = new Intent(this, GeschiedenisAdminActivity.class);
            startActivity(toGeschiedenisAdmin);
        }
        else if (v == vakantieWerknemersBtn){
            Intent tovakantieWerknemer = new Intent(this, AdminVakantieWerknemerActivity.class);
            startActivity(tovakantieWerknemer);
        }
    }
}
