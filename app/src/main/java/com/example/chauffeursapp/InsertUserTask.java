package com.example.chauffeursapp;

import android.util.Log;

public class InsertUserTask implements Runnable{

    AppDatabase db;
    User user;

    public InsertUserTask(AppDatabase db, User user){
        this.db = db;
        this.user = user;
    }

    @Override
    public void run() {
        db.userDAO().InsertUser(this.user);
        String name = db.userDAO().getAll().get(0).getName();
        Log.d("Inserted", name);
    }
}
