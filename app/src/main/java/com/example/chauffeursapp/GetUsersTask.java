package com.example.chauffeursapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GetUsersTask extends AppCompatActivity implements Runnable{

    AppDatabase db;
    GeschiedenisAdminActivity gaa;

    public GetUsersTask(AppDatabase db, GeschiedenisAdminActivity gaa){
        this.gaa = gaa;
        this.db = db;
    }

    @Override
    public void run() {
        List<User> namen = db.userDAO().getAll();
        User[] users = new User[namen.size()];

        for (int i=0; i<namen.size(); i++) {
            users[i] = new User(namen.get(i).getName(), namen.get(i).getEmail(), namen.get(i).getRol(),namen.get(i).getUuid());
        }

        gaa.setAdapter(users);
    }
}
