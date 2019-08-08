package com.example.sku.activities.product_register_detailed.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DetailDAO {

    //get all list
    @Query("SELECT * FROM mymodelsavedb")
    List<MyModelSaveDB> getAllMyModelSaveDB();


    //add
    @Insert
    void insertAll(MyModelSaveDB myModelSaveDB);


//    //search
//    @Query("SELECT * FROM mymodelsavedb WHERE value = :name")
//    List<MyModelSaveDB> findProduct(String name);


    // delete each item (use in adapter)
    @Delete
    void deleteItem(MyModelSaveDB myModelSaveDB);

    //delete all
    @Query("DELETE FROM mymodelsavedb")
    void deleteAll();


    @Update
    void updateItem(MyModelSaveDB myModelSaveDB);
}
