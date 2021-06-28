package com.example.chauffeursapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity //om hem een model in de db te maken
public class User {

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String email;

    @ColumnInfo
    private int uren;

    @PrimaryKey
    private int uuid;

    public User (String name, String email, int uren, int uuid){
        this.name = name;
        this.email = email;
        this.uren = uren;
        this.uuid = uuid;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public int getUren(){
        return this.uren;
    }

    public int getUuid(){
        return  this.uuid;
    }

}
