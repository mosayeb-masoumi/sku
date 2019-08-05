package com.example.sku.models.sub_categoryList_spn;

import com.google.gson.annotations.SerializedName;

public class SubCategory2 {
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
