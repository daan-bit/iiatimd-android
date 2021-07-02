package com.example.chauffeursapp;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdminGetWerktijdenUserTask extends AppCompatActivity implements Runnable{

    AppDatabase db;
    int id;
    AdminUserWerktijdenAcitivity auwa;

    public AdminGetWerktijdenUserTask(AppDatabase db, int id, AdminUserWerktijdenAcitivity auwa){
        this.db = db;
        this.id = id;
        this.auwa = auwa;
    }


    @Override
    public void run() {
        Log.d("id", String.valueOf(id));
        List<Werktijden> tijden = db.werktijdenDAO().getByUser(id);
        Log.d("tijden", String.valueOf(tijden));
        Werktijden[] werktijden = new Werktijden[tijden.size()];

        for (int i=0; i<tijden.size(); i++) {
            werktijden[i] = new Werktijden(tijden.get(i).getId(), tijden.get(i).getUser_id(), tijden.get(i).getBegin_shift(), tijden.get(i).getEinde_shift());
        }
        auwa.setAdapter(werktijden);
    }
}
