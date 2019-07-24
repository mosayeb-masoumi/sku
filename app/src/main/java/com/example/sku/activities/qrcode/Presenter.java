package com.example.sku.activities.qrcode;

import android.content.Context;

public class Presenter implements Contract.Presenter {
    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this,context);
    }

    @Override
    public void btnRegisterPressed(String barcodResult) {

        view.hideBtn();
        model.requestSendBarcode(barcodResult);


    }
}
