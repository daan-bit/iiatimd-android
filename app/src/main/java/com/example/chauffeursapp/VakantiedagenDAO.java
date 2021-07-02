package com.example.chauffeursapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VakantiedagenDAO {

    @Query("SELECT * FROM vakantiedagen")
    List<Vakantiedagen> getAll();

    @Query("SELECT * FROM vakantiedagen")
    List<Vakantiedagen> getVakantiedagenUser();

    @Query("SELECT * FROM vakantiedagen WHERE inLaravelDB = 0")
    List<Vakantiedagen> sendVakantieDagenUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertVakantiedagen(Vakantiedagen Vakantiedagen);

    @Delete
    void delete(Vakantiedagen vakantiedagen);

    @Update
    void update(Vakantiedagen vakantiedagen);


}
