package com.example.sku.models.register_shop;

import com.google.gson.annotations.SerializedName;

public class RegisterShop {
    @SerializedName("id")
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
