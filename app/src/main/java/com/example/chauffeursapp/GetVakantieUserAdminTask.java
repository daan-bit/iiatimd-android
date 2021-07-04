package com.example.chauffeursapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GetVakantieUserAdminTask extends AppCompatActivity implements Runnable{
    AppDatabase db;
    AdminVakantieWerknemerActivity avw;

    public GetVakantieUserAdminTask(AppDatabase db, AdminVakantieWerknemerActivity avw){
        this.avw = avw;
        this.db = db;
    }

    @Override
    public void run() {
        List<User> gebruikers = db.userDAO().getAllWerknemer();
        User[] users = new User[gebruikers.size()];
        for (int i=0; i<gebruikers.size(); i++) {
            users[i] = new User(gebruikers.get(i).getName(), gebruikers.get(i).getEmail(), gebruikers.get(i).getRol(), gebruikers.get(i).getUuid());
        }

        avw.setAdapter(users);
    }
}