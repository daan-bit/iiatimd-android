package com.example.chauffeursapp;

import android.util.Log;

public class GetWerktijdenTask implements Runnable{

    AppDatabase db;

    public GetWerktijdenTask(AppDatabase db){
        this.db = db;
    }

    @Override
    public void run() {
        int id = db.werktijdenDAO().getLast().get(0).getId();
        Log.d("id laatste db insert", String.valueOf(id));
    }
}
