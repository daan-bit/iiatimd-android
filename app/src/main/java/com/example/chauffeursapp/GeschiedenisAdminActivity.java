package com.example.chauffeursapp;

import android.os.Bundle;
import android.widget.Button;

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

        recyclerView = findViewById(R.id.recyclerViewAdmin);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.hasFixedSize();

        final User[] users = new User[6];
        users[0] = new User("Marina", "Joejoe@gmail.com", "werknemer", 1);
        users[1] = new User("Youri", "Joejoe@gmail.com", "werknemer", 2);
        users[2] = new User("Pieter", "Joejoe@gmail.com", "werknemer", 3);
        users[3] = new User("Zwanus", "Joejoe@gmail.com", "werknemer", 4);
        users[4] = new User("Nemo", "Joejoe@gmail.com", "werknemer", 5);
        users[5] = new User("Leo", "Joejoe@gmail.com", "werknemer", 6);

        recyclerViewAdapter = new UserAdapter(users);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
