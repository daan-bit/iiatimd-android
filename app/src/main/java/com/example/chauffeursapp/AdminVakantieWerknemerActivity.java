package com.example.chauffeursapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
        recyclerViewAdapter = new GetUserVakantieAdapter(users, this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void toAdminUserVakantiedagen(int id){
        Bundle bundleForAdminUserVakantiedagen = new Bundle();
        bundleForAdminUserVakantiedagen.putInt("id", id);
        Intent toAdminUserVakantiedagen = new Intent(this, AdminUserVakantiedagenActivity.class);
        toAdminUserVakantiedagen.putExtras(bundleForAdminUserVakantiedagen);
        startActivity(toAdminUserVakantiedagen);
    }
}
