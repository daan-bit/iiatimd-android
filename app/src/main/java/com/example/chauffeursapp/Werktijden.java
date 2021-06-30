package com.example.chauffeursapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

import java.time.LocalDateTime;

@Entity //om hem een model in de db te maken
public class Werktijden {

    @PrimaryKey
    private int id;

    @ForeignKey(entity = User.class, parentColumns = {"uuid"}, childColumns = {"users"})
    private int user_id;



    @ColumnInfo
    private String begin_shift;

    @ColumnInfo
    private String aantal_uren_gewerkt;

    @ColumnInfo
    private String aantal_uren_pauze;

    @ColumnInfo
    private String einde_shift;



    public Werktijden (int id, int user_id, String begin_shift, String aantal_uren_gewerkt, String aantal_uren_pauze, String einde_shift){
        this.id = id;
        this.user_id = user_id;
        this.begin_shift = begin_shift;
        this.aantal_uren_gewerkt = aantal_uren_gewerkt;
        this.aantal_uren_pauze = aantal_uren_pauze;
        this.einde_shift = einde_shift;

    }

    public int getId(){return this.id;}
    public int getUser_id(){return this.user_id;}
    public String getBegin_shift(){return this.begin_shift;}
    public String getAantal_uren_gewerkt(){return this.aantal_uren_gewerkt;}
    public String getAantal_uren_pauze(){return this.aantal_uren_pauze;}
    public String getEinde_shift(){return this.einde_shift;}



}
