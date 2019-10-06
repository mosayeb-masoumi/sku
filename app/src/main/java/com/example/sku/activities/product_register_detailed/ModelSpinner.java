package com.example.sku.activities.product_register_detailed;

public class ModelSpinner {

    private String spnId;
    private String value;

    public ModelSpinner(String spnId, String value) {
        this.spnId = spnId;
        this.value = value;
    }

    public String getSpnId() {
        return spnId;
    }

    public void setSpnId(String spnId) {
        this.spnId = spnId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
