package com.example.chauffeursapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GetUrenTask extends AppCompatActivity implements Runnable{

    AppDatabase db;
    GeschiedenisUserActivity gua;

    public GetUrenTask(AppDatabase db, GeschiedenisUserActivity gua){
        this.gua = gua;
        this.db = db;
    }

    @Override
    public void run() {
        List<Werktijden> werktijden = db.werktijdenDAO().getAll();
        Werktijden[] uren = new Werktijden[werktijden.size()];
        for (int i=0; i<werktijden.size(); i++) {
            uren[i] = new Werktijden(werktijden.get(i).getId(), werktijden.get(i).getUser_id(), werktijden.get(i).getBegin_shift(),werktijden.get(i).getEinde_shift(), werktijden.get(i).getInLaravelDB());
        }

        gua.setAdapter(uren);
    }
}
