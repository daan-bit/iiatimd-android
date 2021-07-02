package com.example.chauffeursapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WerktijdenDAO {

    @Query("SELECT * FROM werktijden")
    List<Werktijden> getAll();

    @Query("SELECT * FROM werktijden WHERE user_id= :id")
    List<Werktijden> getByUser(Integer id);

    @Query("SELECT * FROM werktijden ORDER BY id DESC")
    List<Werktijden> getLast();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertWerktijden(Werktijden werktijden);

    @Delete
    void delete(Werktijden werktijden);

    //@Query("UPDATE werktijden SET einde_shift = :einde_shift WHERE begin_shift = :begin_shift")
    //void updateAantalUrenGewerkt(Werktijden werktijden);


    @Update
    void update(Werktijden werktijden);


}
