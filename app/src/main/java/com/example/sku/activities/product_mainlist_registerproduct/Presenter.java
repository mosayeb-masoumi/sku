package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.Context;
import android.widget.Toast;

import com.example.sku.R;

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
    public void viewLoaded() {

        view.showLoading();
        model.requestSpnLists();
    }

    @Override
    public void responseResult(int result) {
        if(result == 1){
            view.hideLoading();
            view.setSpnOwner();
            view.setSpnBrand();
            view.setSpnCategory();
        }else if(result == -4){
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();
        }else if(result == -5){
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void requestDataSpnSubBrand(int itemPositionBrand) {

        view.showLoading();
        model.requestSpnSubBrand(itemPositionBrand);
    }

}
