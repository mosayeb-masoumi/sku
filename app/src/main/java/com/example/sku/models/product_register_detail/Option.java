package com.example.sku.models.product_register_detail;

import com.google.gson.annotations.SerializedName;

public class Option {

    @SerializedName("title")
    public String title;
    @SerializedName("id")
    public String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
