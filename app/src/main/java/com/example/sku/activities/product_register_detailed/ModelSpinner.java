package com.example.sku.activities.product_register_detailed;

import com.google.gson.annotations.SerializedName;

public class ModelSpinner {

    @SerializedName("id")
    private int id;
    @SerializedName("spnId")
    private String spnId;
    @SerializedName("value")
    private String value;

    public ModelSpinner(int id, String spnId, String value) {
        this.id = id;
        this.spnId = spnId;
        this.value = value;
    }

    public ModelSpinner() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpnId() {
        return spnId;
    }

    public void setSpnId(String spnId) {
        this.spnId = spnId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
