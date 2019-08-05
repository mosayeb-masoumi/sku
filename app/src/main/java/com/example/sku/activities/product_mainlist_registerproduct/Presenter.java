package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.sku.R;
import com.example.sku.activities.main.MainActivity;
import com.example.sku.helpers.App;


public class Presenter implements Contract.Presenter {
    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();


    boolean status = true;

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this, context);
    }

    @Override
    public void viewLoaded() {

        view.showLoading();
        model.requestSpnLists();
    }

    @Override
    public void responseResult(int result) {
        view.hideLoading();
        if (result == 1) {
            view.setSpnOwner();
            view.setSpnBrand();
            view.setSpnCategory();
        } else if (result == -4) {
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();
        } else if (result == -5) {
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void requestDataSpnSubBrand(int itemPositionBrand) {

        view.showLoading();
        String spnBrandId = App.totalSpnLists.data.brand.get(itemPositionBrand).id;
        model.requestSpnSubBrand(spnBrandId);
    }

    @Override
    public void requestDataSpnSubCategory(int itemPositionSpnCategory) {
        view.showLoading();


        String spnCategoryId = App.totalSpnLists.data.subCategory.get(itemPositionSpnCategory).id;

        model.requestSpnSubCategory(spnCategoryId);
    }


    @Override
    public void responseResultSpnSubBrand(int result) {
        if (result == 1) {
            view.hideLoading();
            view.setSpnSubBrand();
        } else if (result == -4) {
            view.hideLoading();
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();

        } else if (result == -5) {
            view.hideLoading();
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void responseResultSpnSubCategoryList2(int result) {
        if (result == 1) {
            view.hideLoading();
            view.setSpnSubCategory();
        } else if (result == -4) {
            view.hideLoading();
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();

        } else if (result == -5) {
            view.hideLoading();
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void btnRegisterPressed(String edtCategory, String edtSubCategoty, String edtOwner, String edtBrand, String edtSubBrand,
                                   int spnCategoryPosition, int spnSubCategoryPosition, int spnOwnerPosition, int spnBrandPosition, int spnSubBrandPosition) {




        if (spnCategoryPosition == 0) {

            if(edtCategory.equals("") && edtSubCategoty.equals("")){
                view.setErrorEdtCategory("لطفا گروه را تایپ نمایید");
                view.setErrorEdtSubCategory("لطفا زیرگروه را تایپ نمایید");
                status =false;
            } else if (edtCategory.equals("")) {
                view.setErrorEdtCategory("لطفا گروه را تایپ نمایید");
                status =false;
            } else if (edtSubCategoty.equals("")) {
                view.setErrorEdtSubCategory("لطفا زیرگروه را تایپ نمایید");
                status =false;
            }else{
                status=true;
            }
        }

        if (spnOwnerPosition == 0) {
            if (edtOwner.equals("")) {
                view.setErrorEdtOwner("لطفا تولیدکننده یا وارد کننده را تایپ نمایید");
                status =false;
            }else{
                status=true;
            }
        }
         if(spnBrandPosition==0){
             if (edtBrand.equals("") && edtSubBrand.equals("")) {
                 view.setErrorEdtBrand("لطفا برند را تایپ نمایید");
                 view.setErrorEdtSubBrand("لطفا زیربرند را تایپ نمایید");
                 status =false;
             } else if(edtBrand.equals("")){
                 view.setErrorEdtBrand("لطفا برند را تایپ نمایید");
                 status =false;
             }
             else if (edtSubBrand.equals("")) {
                 view.setErrorEdtSubBrand("لطفا زیربرند را تایپ نمایید");
                 status =false;
             }else if(edtOwner.equals("")){
                 view.setErrorEdtOwner("لطفا تولیدکننده یا وارد کننده را تایپ نمایید");                                ///////////////////////////
                 status =false;
             }else if(edtCategory.equals("")){
                 view.setErrorEdtCategory("لطفا گروه را تایپ نمایید");
                 status =false;
             }else if(edtSubCategoty.equals("")){
                 view.setErrorEdtSubCategory("لطفا زیرگروه را تایپ نمایید");
                 status =false;
             }
             else{
                 status=true;
             }

         }


        if (App.idSpnFamily.equals("")) {
            context.startActivity(new Intent(context, MainActivity.class));
            Toast.makeText(context, "لطفا خانواده ی محصول را انتخاب کنید", Toast.LENGTH_SHORT).show();
        } else if (App.idSpnShop.equals("")) {
            context.startActivity(new Intent(context, MainActivity.class));
            Toast.makeText(context, "لطفا فروشگاه را انتخاب کنید", Toast.LENGTH_SHORT).show();

        } else if (status){
            view.hideBtn();
            model.requestRegisterProduct(edtCategory, edtSubCategoty, edtOwner, edtBrand, edtSubBrand,
                    spnCategoryPosition, spnSubCategoryPosition, spnOwnerPosition, spnBrandPosition, spnSubBrandPosition);
        }

    }

    @Override
    public void productRegisterSendResult(int result) {
        view.showBtn();
        if(result ==1){

        }else if(result==-4){
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();
        }else if(result == -5){
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }
}
