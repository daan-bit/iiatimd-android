package com.example.chauffeursapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdminVakantieWerknemerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_uservakantiedagen);

        recyclerView = findViewById(R.id.AdminVakantieUserRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        new Thread(new GetVakantieUserAdminTask(db, this)).start();

    }

    public void setAdapter(User[] users){
        recyclerViewAdapter = new GetUserVakantieAdapter(users);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
