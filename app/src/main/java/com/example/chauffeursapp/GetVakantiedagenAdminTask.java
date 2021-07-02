package com.example.chauffeursapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GetVakantiedagenAdminTask extends AppCompatActivity implements Runnable{
    AppDatabase db;
    AdminVakantieWerknemerActivity avw;

    public GetVakantiedagenAdminTask(AppDatabase db, AdminVakantieWerknemerActivity avw){
        this.avw = avw;
        this.db = db;
    }

    @Override
    public void run() {
        List<Vakantiedagen> dagen = db.vakantiedagenDAO().getAll();
        Vakantiedagen[] vrij = new Vakantiedagen[dagen.size()];
        for (int i=0; i<dagen.size(); i++) {
            vrij[i] = new Vakantiedagen(dagen.get(i).getId(), dagen.get(i).getUser_id(), dagen.get(i).getVakantie_van(), dagen.get(i).getVakantie_tot(), dagen.get(i).getInLaravelDB());
        }

        avw.setAdapter(vrij);
    }
}
