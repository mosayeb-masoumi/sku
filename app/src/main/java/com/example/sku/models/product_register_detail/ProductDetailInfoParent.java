package com.example.sku.models.product_register_detail;

import com.google.gson.annotations.SerializedName;

public class ProductDetailInfoParent {

    @SerializedName("data")
    public ProductDetailInfoChild data;

    public ProductDetailInfoChild getData() {
        return data;
    }

    public void setData(ProductDetailInfoChild data) {
        this.data = data;
    }
}
