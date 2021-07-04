package com.example.chauffeursapp;

import android.content.Context;
import android.util.Log;

import java.util.List;

public class InsertVakantiedagenLaravelTask implements Runnable{

    AppDatabase db;
    Context context;
    public InsertVakantiedagenLaravelTask(AppDatabase db, Context context){
        this.db = db;
        this.context = context;
    }


    @Override
    public void run() {
        List<Vakantiedagen> vakantiedagen = db.vakantiedagenDAO().sendVakantieDagenUser();
        if(vakantiedagen.size() >= 1) {
            Vakantiedagen[] vakantiedagens = new Vakantiedagen[vakantiedagen.size()];
            for (int i = 0; i < vakantiedagen.size(); i++) {
                vakantiedagens[i] = new Vakantiedagen(vakantiedagen.get(i).getId(), vakantiedagen.get(i).getUser_id(), vakantiedagen.get(i).getVakantie_van(), vakantiedagen.get(i).getVakantie_tot(), vakantiedagen.get(i).getInLaravelDB());
                APICalls.sendVakantieDagen(vakantiedagen.get(i).getUser_id(), vakantiedagen.get(i).getVakantie_van(), vakantiedagen.get(i).getVakantie_tot(), vakantiedagen.get(i).getInLaravelDB(), context);
            }
        }


    }
}
