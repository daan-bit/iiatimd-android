package com.example.chauffeursapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GetWerktijdenUserTask extends AppCompatActivity implements Runnable{

    AppDatabase db;
    GeschiedenisUserActivity gua;

    public GetWerktijdenUserTask(AppDatabase db, GeschiedenisUserActivity gua){
        this.gua = gua;
        this.db = db;
    }

    @Override
    public void run() {
        List<Werktijden> werktijden = db.werktijdenDAO().getAll();
        Werktijden[] werktijdens = new Werktijden[werktijden.size()];
        for (int i=0; i<werktijden.size(); i++) {
            werktijdens[i] = new Werktijden(werktijden.get(i).getId(), werktijden.get(i).getUser_id(), werktijden.get(i).getBegin_shift(),werktijden.get(i).getEinde_shift());
        }

        gua.setAdapter(werktijdens);
    }
}
