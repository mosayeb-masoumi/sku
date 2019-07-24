package com.example.sku.activities.qrcode;

import android.content.Context;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter , Context context) {
        this.presenter = presenter;
        this.context=context;
    }

    @Override
    public void requestSendBarcode(String barcodResult) {

    }
}