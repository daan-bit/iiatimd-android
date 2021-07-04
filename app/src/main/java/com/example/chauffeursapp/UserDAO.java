package com.example.chauffeursapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

//Hebben user in db (entity) en deze klasse zorgt ervoor dat we ermee kunnen praten
@Dao
public interface UserDAO{

    //Returnt een lijst van users en die functie heet getAll, hoeft niet uit te leggen hoe precies werkt, als deze methode maar gebruikt wordt
    @Query("SELECT * FROM user")
    List<User> getAll();


    @Query("SELECT * FROM user WHERE rol = 'werknemer'")
    List<User> getAllWerknemer();

    @Query("SELECT COUNT (*) FROM user")
    int getcount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertUser(User user);

    @Delete
    void delete(User user);


}
