package com.example.chauffeursapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;

public class WerknemerVakantiedagenActivity extends AppCompatActivity {
    private static final String TAG = "WerknemerVakantiedagenActivity";
    private TextView vakantieVan;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private DatePickerDialog.OnDateSetListener vakantieTotSetListener;
    private TextView vakantieTot;
    private Button vakantieToevoegen;
    int u_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_werknemer_vakantiedagen);
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        u_id = bundle.getInt("user_id");
        vakantieVan = (TextView) findViewById(R.id.vakantieVanDate);
        vakantieTot = (TextView) findViewById(R.id.vakantieTotDate);
        vakantieToevoegen = (Button) findViewById(R.id.vakantieToevoegen);
        vakantieVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        WerknemerVakantiedagenActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        vakantieTot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        WerknemerVakantiedagenActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        vakantieTotSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                String date = dayOfMonth + "-" + month + "-" + year;
                vakantieVan.setText(date);
            }
        };

        vakantieTotSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                String date = dayOfMonth + "-" + month + "-" + year;
                vakantieTot.setText(date);
            }
        };

        vakantieToevoegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vakantiedagen[] vakantiedag = new Vakantiedagen[1];
                vakantiedag[0] = new Vakantiedagen(0, u_id, vakantieVan.getText().toString(), vakantieTot.getText().toString(), 0);
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        db.vakantiedagenDAO().InsertVakantiedagen(vakantiedag[0]);
                    }

                });
                vakantieVan.setText("");
                vakantieTot.setText("");


                Toast.makeText(WerknemerVakantiedagenActivity.this, "U hebt successvol de vakantiedagen doorgegeven!", Toast.LENGTH_SHORT).show(); // chauffeur klaar met rijden

            }
        });






    }
}