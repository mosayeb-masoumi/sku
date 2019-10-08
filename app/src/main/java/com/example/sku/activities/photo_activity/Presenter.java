package com.example.sku.activities.photo_activity;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.sku.R;
import com.example.sku.activities.qrcode.QRCodeActivity;

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
    public void btSndPicsPressed(String strBm1, String strBm2, String strBm3, String strBm4) {
        view.hidebtn();
        model.requestSendPics(strBm1,strBm2,strBm3,strBm4);
    }

    @Override
    public void sendpisResult(int result) {
        view.showbtn();

        if(result==1){
            context.startActivity(new Intent(context,QRCodeActivity.class));
        }else if(result== -4){
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();
        }else if(result== -5){
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }
}
