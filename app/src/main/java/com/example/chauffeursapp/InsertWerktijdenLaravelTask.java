package com.example.chauffeursapp;

import android.content.Context;
import android.util.Log;

import java.util.List;

public class InsertWerktijdenLaravelTask implements Runnable{

    AppDatabase db;
    Context context;
    public InsertWerktijdenLaravelTask(AppDatabase db, Context context){
        this.db = db;
        this.context = context;
    }


    @Override
    public void run() {
        List<Werktijden> werktijden = db.werktijdenDAO().sendWerktijdenUser();


       Werktijden[] werktijdens = new Werktijden[werktijden.size()];
        for(int i=0; i< werktijden.size(); i++) {
            werktijdens[i] = new Werktijden(werktijden.get(i).getId(), werktijden.get(i).getUser_id(), werktijden.get(i).getBegin_shift(),werktijden.get(i).getEinde_shift(), werktijden.get(i).getInLaravelDB());


            APICalls.sendWerktijden(werktijden.get(i).getId(), werktijden.get(i).getUser_id(), werktijden.get(i).getBegin_shift(), werktijden.get(i).getEinde_shift(), werktijden.get(i).getInLaravelDB(), context);
        }


    }
}
