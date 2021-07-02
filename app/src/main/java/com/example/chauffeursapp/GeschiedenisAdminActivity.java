package com.example.chauffeursapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GeschiedenisAdminActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geschiedenisadmin);

        recyclerView = findViewById(R.id.recyclerViewUser);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        new Thread(new GetUsersTask(db, this)).start();

    }

    public void setAdapter(User[] users){
        recyclerViewAdapter = new UserAdapter(users, this);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void toAdminUserWerktijden(String name){
        Bundle bundleForAdminUserWerktijden = new Bundle();
        bundleForAdminUserWerktijden.putString("name", name);
        Intent toAdminUserWerktijden = new Intent(this, AdminUserWerktijdenAcitivity.class);
        toAdminUserWerktijden.putExtras(bundleForAdminUserWerktijden);
        startActivity(toAdminUserWerktijden);
    }




}
