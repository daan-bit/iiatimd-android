package com.example.chauffeursapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


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
    }


    @Override
    public void onClick(View v) {
        if (addPass.getText().toString().equals(addPass2.getText().toString())) {
            APICalls.accountAanmaken(addEmail.getText().toString(), addPass.getText().toString(), addName.getText().toString(), this.getApplicationContext());
            Intent toAdminDashboard = new Intent(this, DashboardAdminActivity.class);
            startActivity(toAdminDashboard);
        }else {
            Toast.makeText(WerknemerToevoegenActivity.this, "Wachtwoorden ongelijk", Toast.LENGTH_SHORT).show();
        }
    }



}

