package com.example.sku.activities.barcode_list;

import android.content.Context;
import android.content.Intent;

import com.example.sku.activities.product_mainlist_registerproduct.RegisterProduct_ProductMainList;
import com.example.sku.activities.qrcode.QRCodeActivity;
import com.example.sku.activities.qrcode.QRcodeScaner;

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
    public void btnProductRegisterPressed() {
//        view.hideBtn();
//        model.requestGetListOfSpinners();

        context.startActivity(new Intent(context, RegisterProduct_ProductMainList.class));

//        model.requestProductMainList();
    }

    @Override
    public void btnBarcodeRegisterPressed() {

        context.startActivity(new Intent(context, QRCodeActivity.class));
    }
}
