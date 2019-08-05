package com.example.sku.activities.qrcode;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.sku.R;
import com.example.sku.activities.barcode_list.BarcodeListActivity;

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

    @Override
    public void barcodeProductsList(int result) {
        view.showBtn();
        if (result==1){
            view.showBtn();
        }else if(result==-4){
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();
        }else if(result == -5){
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }


}
