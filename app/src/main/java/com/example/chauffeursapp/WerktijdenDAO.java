package com.example.chauffeursapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WerktijdenDAO {

    @Query("SELECT * FROM werktijden")
    List<Werktijden> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertWerktijden(Werktijden werktijden);

    @Delete
    void delete(Werktijden werktijden);


}
