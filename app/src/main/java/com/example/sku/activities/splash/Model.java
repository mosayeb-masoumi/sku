package com.example.sku.activities.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import android.widget.Toast;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter ,Context context) {
        this.presenter = presenter;
        this.context=context;
    }





    @Override
    public void requestLogin() {
        Toast.makeText(context, "ss", Toast.LENGTH_SHORT).show();
    }
}
