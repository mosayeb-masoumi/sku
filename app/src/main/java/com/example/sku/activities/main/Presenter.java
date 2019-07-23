package com.example.sku.activities.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.example.sku.R;
import com.example.sku.activities.new_family.NewFamilyActivity;
import com.example.sku.activities.new_shop.NewShopActivity;

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
    public void loadView() {
        model.requestShopList();
    }



    @Override
    public void sopListResult(int result) {
        if(result == -4){
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();
        }else if(result == -5){
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void btnNewShopPressed() {
        context.startActivity(new Intent(context, NewShopActivity.class));
    }


}
