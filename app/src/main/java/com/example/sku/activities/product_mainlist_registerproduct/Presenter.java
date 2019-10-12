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
            view.setSpnCountry();
            view.setSpnCompany();
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
    public void btnRegisterPressed(String edtCategory, String edtSubCategoty, String edtProducer, String edtCompany_owner, String edtBrand,
                                   String edtSubBrand, String edtCost,
                                   int spnCategoryPosition, int spnSubCategoryPosition, int spnProducer_importerPosition, int spnOwner_companyPosition,
                                   int spnBrandPosition, int spnSubBrandPosition, int spnCountryPosition) {

        boolean status = true;

        // category validation
        if (spnCategoryPosition == 0) {
            if (edtCategory.equals("") && edtSubCategoty.equals("")) {
                view.setErrorEdtCategory("لطفا گروه را تایپ نمایید");
                view.setErrorEdtSubCategory("لطفا زیرگروه را تایپ نمایید");
                Toast.makeText(context, "لطفا گروه و زیرگروه را تایپ نمایید", Toast.LENGTH_SHORT).show();
                status = false;
            }
        }
        if (spnCategoryPosition == 0) {
            if (edtCategory.equals("") && !edtSubCategoty.equals("")) {
                view.setErrorEdtCategory("لطفا گروه را تایپ نمایید");
                Toast.makeText(context, "لطفا گروه را تایپ نمایید", Toast.LENGTH_SHORT).show();
                status = false;
            }
        }
        if (spnCategoryPosition == 0) {
            if (!edtCategory.equals("") && edtSubCategoty.equals("")) {
                view.setErrorEdtSubCategory("لطفا زیرگروه را تایپ نمایید");
                Toast.makeText(context, "لطفا زیر گروه را تایپ نمایید", Toast.LENGTH_SHORT).show();
                status = false;
            }
        }
        if (spnCategoryPosition > 0 && spnSubCategoryPosition == 0) {
            if (edtSubCategoty.equals("")) {
                view.setErrorEdtSubCategory("لطفا زیرگروه را تایپ نمایید");
                Toast.makeText(context, "لطفا زیرگروه را تایپ نمایید", Toast.LENGTH_SHORT).show();
                status = false;
            }
        }


        // producer validation
        if (spnProducer_importerPosition == 0) {
            if (edtProducer.equals("")) {
                view.setErrorEdtOwner("لطفا تولیدکننده یا وارد کننده را تایپ نمایید");
                Toast.makeText(context, "لطفا تولیدکننده یا وارد کننده را تایپ نمایید", Toast.LENGTH_SHORT).show();
                status = false;
            }
        }


        // owner validation
        if (spnOwner_companyPosition == 0) {
            if (edtCompany_owner.equals("")) {
                view.setErrorEdtCompany("لطفا کمپانی را وارد نمایید");
                Toast.makeText(context, "لطفا کمپانی را وارد نمایید", Toast.LENGTH_SHORT).show();
                status = false;
            }
        }


        // brand and subbrand validation
        if (spnBrandPosition == 0) {
            if (edtBrand.equals("") && edtSubBrand.equals("")) {
                view.setErrorEdtBrand("لطفا برند را تایپ نمایید");
                view.setErrorEdtSubBrand("لطفا زیربرند را تایپ نمایید");
                Toast.makeText(context, "لطفا برند و زیربرند را تایپ نمایید", Toast.LENGTH_SHORT).show();
                status = false;
            }
        }
        if (spnBrandPosition == 0) {
            if (edtBrand.equals("") && !edtSubBrand.equals("")) {
                view.setErrorEdtBrand("لطفا برند را تایپ نمایید");
                Toast.makeText(context, "لطفا برند را تایپ نمایید", Toast.LENGTH_SHORT).show();
                status = false;
            }
        }
        if (spnBrandPosition == 0) {
            if (!edtBrand.equals("") && edtSubBrand.equals("")) {
                view.setErrorEdtSubBrand("لطفا زیربرند را تایپ نمایید");
                Toast.makeText(context, "لطفا زیربرند را تایپ نمایید", Toast.LENGTH_SHORT).show();
                status = false;
            }
        }
        if (spnBrandPosition > 0 && spnSubBrandPosition == 0) {
            if (edtSubBrand.equals("")) {
                view.setErrorEdtSubBrand("لطفا زیربرند را تایپ نمایید");
                Toast.makeText(context, "لطفا زیربرند را تایپ نمایید", Toast.LENGTH_SHORT).show();
                status = false;
            }
        }


        // cost validation
        if (edtCost.equals("") || edtCost.isEmpty()) {
            view.setErrorEdtCost("لطفا قیمت را وارد نمایید");
            Toast.makeText(context, "لطفا قیمت را وارد نمایید", Toast.LENGTH_SHORT).show();
            status = false;
        }



        if (App.idSpnFamily.equals("")) {
            context.startActivity(new Intent(context, MainActivity.class));
            Toast.makeText(context, "لطفا خانواده ی محصول را انتخاب کنید", Toast.LENGTH_SHORT).show();
        } else if (App.idSpnShop.equals("")) {
            context.startActivity(new Intent(context, MainActivity.class));
            Toast.makeText(context, "لطفا فروشگاه را انتخاب کنید", Toast.LENGTH_SHORT).show();

        } else if (status) {
            view.hideBtn();
            model.requestRegisterProduct(edtCategory, edtSubCategoty, edtProducer, edtCompany_owner, edtBrand, edtSubBrand, edtCost,
                    spnCategoryPosition, spnSubCategoryPosition, spnProducer_importerPosition, spnOwner_companyPosition,
                    spnBrandPosition, spnSubBrandPosition, spnCountryPosition);
        }

    }

    @Override
    public void productRegisterSendResult(int result) {
        view.showBtn();
        if (result == 1) {

        } else if (result == -4) {
            Toast.makeText(context, R.string.serverFaield, Toast.LENGTH_SHORT).show();
        } else if (result == -5) {
            Toast.makeText(context, R.string.connectionFaield, Toast.LENGTH_SHORT).show();
        }
    }
}
