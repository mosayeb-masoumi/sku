package com.example.sku.activities.product_register_detailed.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class MyModelSaveDB {

    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "spnId")
    private int spnId;

    @ColumnInfo(name = "value")
    private String value;


    public MyModelSaveDB(int spnId, String value) {
        this.spnId = spnId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpnId() {
        return spnId;
    }

    public void setSpnId(int spnId) {
        this.spnId = spnId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
