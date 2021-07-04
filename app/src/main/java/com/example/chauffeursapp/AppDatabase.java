package com.example.chauffeursapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;


@Database(entities = {User.class, Werktijden.class, Vakantiedagen.class}, version = 22) //Kan ook meerdere entities opslaan

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
    public abstract WerktijdenDAO werktijdenDAO();
    public abstract VakantiedagenDAO vakantiedagenDAO();

    private static AppDatabase instance; //static == kan erbij ook als je geen instance van AppDatabase hebt

    static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = create(context);
        }
        return  instance;
    }

    private static AppDatabase create(final Context context){
        return Room.databaseBuilder(context, AppDatabase.class, "localDB").fallbackToDestructiveMigration().build();
    }


}
