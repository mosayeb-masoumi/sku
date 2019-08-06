package com.example.sku.models.product_register_detail;

import com.google.gson.annotations.SerializedName;

public class ProductDetailInfoChild {

    @SerializedName("id")
    public String id;
    @SerializedName("shop")
    public String shop;
    @SerializedName("category")
    public String category;
    @SerializedName("sub_category")
    public String subCategory;
    @SerializedName("sub_category2")
    public String subCategory2;
    @SerializedName("owner")
    public String owner;
    @SerializedName("brand")
    public String brand;
    @SerializedName("sub_brand")
    public String subBrand;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getSubCategory2() {
        return subCategory2;
    }

    public void setSubCategory2(String subCategory2) {
        this.subCategory2 = subCategory2;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSubBrand() {
        return subBrand;
    }

    public void setSubBrand(String subBrand) {
        this.subBrand = subBrand;
    }
}
