package com.example.chauffeursapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

import java.time.LocalDateTime;

@Entity //om hem een model in de db te maken
public class Vakantiedagen {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = User.class, parentColumns = {"uuid"}, childColumns = {"users"})
    private int user_id;

    @ColumnInfo
    private String vakantie_van;

    @ColumnInfo
    private String vakantie_tot;

    @ColumnInfo
    private int inLaravelDB;



    public Vakantiedagen (int id, int user_id, String vakantie_van, String vakantie_tot, int inLaravelDB){
        this.id = id;
        this.user_id = user_id;
        this.vakantie_van = vakantie_van;
        this.vakantie_tot = vakantie_tot;
        this.inLaravelDB = inLaravelDB;

    }

    public int getId(){return this.id;}
    public int getUser_id(){return this.user_id;}
    public String getVakantie_van(){return this.vakantie_van;}
    public String getVakantie_tot(){return this.vakantie_tot;}
    public int getInLaravelDB(){return this.inLaravelDB;}



}
