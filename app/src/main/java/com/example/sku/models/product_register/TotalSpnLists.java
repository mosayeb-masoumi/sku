package com.example.sku.models.product_register;

import com.google.gson.annotations.SerializedName;

public class TotalSpnLists {

    @SerializedName("data")
    public Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
