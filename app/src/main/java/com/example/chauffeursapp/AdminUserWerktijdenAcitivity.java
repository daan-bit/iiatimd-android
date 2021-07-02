package com.example.chauffeursapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdminUserWerktijdenAcitivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminuserwerktijden);

        Bundle bundle = getIntent().getExtras();

        recyclerView = findViewById(R.id.recyclerViewAdminUserWerktijden);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        new Thread(new AdminGetWerktijdenUserTask(db, bundle.getInt("id"), this)).start();
    }

    public void setAdapter(Werktijden[] werktijden){
        recyclerViewAdapter = new AdminUserWerktijdenAdapter(werktijden);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
