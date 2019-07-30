package com.example.sku.models.product_register;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("sub_category")
    public List<SubCategory> subCategory = null;
    @SerializedName("brand")
    public List<Brand> brand = null;
    @SerializedName("owner")
    public List<Owner> owner = null;

    public List<SubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }

    public List<Owner> getOwner() {
        return owner;
    }

    public void setOwner(List<Owner> owner) {
        this.owner = owner;
    }
}
