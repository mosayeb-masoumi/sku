package com.example.sku.activities.product_register_detailed;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EditTextContentsList {

    @SerializedName("editTextContentsList")
    List<EditTextContents> editTextContentsList = new ArrayList<>();
    @SerializedName("modelSpinnerList")
    List<ModelSpinner> modelSpinnerList = new ArrayList<>();

    private String productId;

    public List<EditTextContents> getEditTextContentsList() {
        return editTextContentsList;
    }

    public void setEditTextContentsList(List<EditTextContents> editTextContentsList) {
        this.editTextContentsList = editTextContentsList;
    }

    public List<ModelSpinner> getModelSpinnerList() {
        return modelSpinnerList;
    }

    public void setModelSpinnerList(List<ModelSpinner> modelSpinnerList) {
        this.modelSpinnerList = modelSpinnerList;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
