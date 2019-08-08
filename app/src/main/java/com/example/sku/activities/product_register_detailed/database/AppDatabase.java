package com.example.sku.activities.product_register_detailed.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {MyModelSaveDB.class} , version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DetailDAO detailDAO();
}
