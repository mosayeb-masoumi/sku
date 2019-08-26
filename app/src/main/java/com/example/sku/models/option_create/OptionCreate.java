package com.example.sku.models.option_create;

import com.google.gson.annotations.SerializedName;

public class OptionCreate {
    @SerializedName("id")
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
