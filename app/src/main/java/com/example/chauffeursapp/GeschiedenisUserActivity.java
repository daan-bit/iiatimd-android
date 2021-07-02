package com.example.chauffeursapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GeschiedenisUserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geschiedenis_user);

        recyclerView = findViewById(R.id.recyclerViewUser);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

       // new Thread(new GetWerktijdenUserTask(db, this)).start();

    }

    public void setAdapter(Werktijden[] werktijdens){
        recyclerViewAdapter = new WerktijdenAdapter(werktijdens);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

}
