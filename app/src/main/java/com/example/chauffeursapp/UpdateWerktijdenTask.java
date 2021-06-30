package com.example.chauffeursapp;

import android.util.Log;

public class UpdateWerktijdenTask implements Runnable{

    AppDatabase db;
    Werktijden werktijden;

    public UpdateWerktijdenTask(AppDatabase db, Werktijden werktijden){
        this.db = db;
        this.werktijden = werktijden;
    }

    @Override
    public void run() {
        db.werktijdenDAO().update(this.werktijden);
        Log.d("geupdate", "werknemer klaar met werken");
    }
}
