package com.example.chauffeursapp;

import android.util.Log;

public class GetUserTask implements Runnable{

    AppDatabase db;

    public GetUserTask(AppDatabase db){
        this.db = db;
    }

    @Override
    public void run() {
        String name = db.userDAO().getAll().get(0).getName();
        Log.d("naam", name);
    }
}
