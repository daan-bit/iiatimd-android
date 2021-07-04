package com.example.chauffeursapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdminUserVakantiedagenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_adminvakantiedagen);

        Bundle bundle = getIntent().getExtras();

        recyclerView = findViewById(R.id.AdminVakantiedagenRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        new Thread(new AdminGetVakantiedagenUserTask(db, bundle.getInt("id"), this)).start();
    }

    public void setAdapter(Vakantiedagen[] vakantiedagen){
        recyclerViewAdapter = new AdminUserVakantiedagenAdapter(vakantiedagen);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
