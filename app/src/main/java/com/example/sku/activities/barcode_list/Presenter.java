package com.example.sku.activities.barcode_list;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.sku.R;
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

    @Override
    public void viewLoaded() {
        model.requestBarcodeProductList();
    }

    @Override
    public void barcodeProductsList(int result) {

        if(result == 1){
            view.setRecyclerview();
        }else if(result==-4){
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();
        }else if(result==-5){
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }
}
