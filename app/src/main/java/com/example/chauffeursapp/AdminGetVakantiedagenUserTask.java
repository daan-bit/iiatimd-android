package com.example.chauffeursapp;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdminGetVakantiedagenUserTask extends AppCompatActivity implements Runnable{

    AppDatabase db;
    int id;
    AdminUserVakantiedagenActivity auva;

    public AdminGetVakantiedagenUserTask(AppDatabase db, int id, AdminUserVakantiedagenActivity auva){
        this.db = db;
        this.id = id;
        this.auva = auva;
    }


    @Override
    public void run() {
        Log.d("id", String.valueOf(id));
        List<Vakantiedagen> dagen = db.vakantiedagenDAO().getVakantieUser(id);
        Log.d("tijden", String.valueOf(dagen));
        Vakantiedagen[] vakantiedagen = new Vakantiedagen[dagen.size()];

        for (int i=0; i<dagen.size(); i++) {
            vakantiedagen[i] = new Vakantiedagen(dagen.get(i).getId(), dagen.get(i).getUser_id(), dagen.get(i).getVakantie_van(), dagen.get(i).getVakantie_tot(), dagen.get(i).getInLaravelDB());
        }
        auva.setAdapter(vakantiedagen);
    }
}

