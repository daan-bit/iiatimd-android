package com.example.chauffeursapp;

import android.util.Log;

public class InsertWerktijdenTask implements Runnable{

    AppDatabase db;
    Werktijden werktijden;

    public InsertWerktijdenTask(AppDatabase db, Werktijden werktijden){
        this.db = db;
        this.werktijden = werktijden;
    }

    @Override
    public void run() {
        db.werktijdenDAO().InsertWerktijden(this.werktijden);
        int opgeslagenId = db.werktijdenDAO().getAll().get(0).getId();

    }
}
