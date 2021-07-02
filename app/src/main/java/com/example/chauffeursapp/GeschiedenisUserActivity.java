package com.example.chauffeursapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GeschiedenisUserActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private androidx.recyclerview.widget.RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geschiedenisuser);

        recyclerView = findViewById(R.id.recyclerViewUser);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        new Thread(new GetUrenTask(db, this)).start();
    }

    public void setAdapter(Werktijden[] werktijden){
        recyclerViewAdapter = new WerktijdenAdapter(werktijden);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
