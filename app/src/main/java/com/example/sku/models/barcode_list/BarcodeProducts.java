package com.example.sku.models.barcode_list;

import com.google.gson.annotations.SerializedName;

public class BarcodeProducts {
    @SerializedName("id")
    public String id;
    @SerializedName("code")
    public String code;
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
    public Object subBrand;
    @SerializedName("image")
    public String image;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Object getSubBrand() {
        return subBrand;
    }

    public void setSubBrand(Object subBrand) {
        this.subBrand = subBrand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
