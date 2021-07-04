package com.example.chauffeursapp;

import android.util.Log;

public class InsertVakantiedagenTask implements Runnable{

    AppDatabase db;
    Vakantiedagen vakantiedagen;

    public InsertVakantiedagenTask(AppDatabase db, Vakantiedagen vakantiedagen){
        this.db = db;
        this.vakantiedagen = vakantiedagen;
    }

    @Override
    public void run() {
        db.vakantiedagenDAO().InsertVakantiedagen(this.vakantiedagen);
        //int opgeslagenId = db.werktijdenDAO().getAll().get(0).getId();

    }
}
