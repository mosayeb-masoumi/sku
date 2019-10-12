package com.example.sku.activities.product_register_detailed;

import android.content.Context;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sku.R;
import com.example.sku.models.product_register_detail.ProductDetailInfoParent;

import java.util.List;

public class Presenter implements Contract.Presenter {
    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    boolean ok204=false;

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this,context);
    }

    @Override
    public void viewLoaded() {
        model.viewLoade();
    }

    @Override
    public void productRegisterDetailDataListResult(int result, String productId) {
        if(result==1){
            view.setRecyclerview();
            ok204=false;
            model.requestRegisterDetailInfo(productId,ok204);
        }else if(result == 204){
            ok204=true;
            model.requestRegisterDetailInfo(productId,ok204);
        }
        else if(result==-4){
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();
        }else if(result==-5){
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void productDetailInfoResult(int result) {

        if(result==1){

        }else if(result==-4){
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();
        }else if(result==-5){
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }


    public void setSpinner(Spinner spinnerRowParent, int position, String SpnRowParentTitleId,String SpnRowParentTitle) {
        view.setSpinner(spinnerRowParent,position , SpnRowParentTitleId,SpnRowParentTitle);
    }


    @Override
    public void sendList(List<EditTextContents> editTextContents, List<ModelSpinner> modelSpinners) {

        model.requestSendList(editTextContents,modelSpinners);
    }

    @Override
    public void setDetailInfoParent(ProductDetailInfoParent response) {
        view.setDetailInfo(response);
    }
}
