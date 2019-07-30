package com.example.sku.models.sub_brandList_spn;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubBrandList {
    @SerializedName("data")
    public List<SubBrand> data = null;

    public List<SubBrand> getData() {
        return data;
    }

    public void setData(List<SubBrand> data) {
        this.data = data;
    }
}
