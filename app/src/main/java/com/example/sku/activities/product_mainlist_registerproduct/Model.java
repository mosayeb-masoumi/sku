package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.sku.activities.product_register_detailed.ProductRegisterDetailActivity;
import com.example.sku.helpers.App;
import com.example.sku.models.product_register.TotalSpnLists;
import com.example.sku.models.product_register.TotalSpnListsSendData;
import com.example.sku.models.product_register_send.ProductRegisterSend;
import com.example.sku.models.product_register_send.ProductRegisterSend_SendData;
import com.example.sku.models.sub_brandList_spn.SubBrandList;
import com.example.sku.models.sub_brandList_spn.SubBrandListSendData;
import com.example.sku.models.sub_categoryList_spn.SubCategoryList2;
import com.example.sku.models.sub_categoryList_spn.SubCategoryList2SendData;
import com.example.sku.services.APIClient;
import com.example.sku.services.APIService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void requestSpnLists() {

        TotalSpnListsSendData totalSpnListsSendData = new TotalSpnListsSendData();

        totalSpnListsSendData.setId(App.idSpnFamily);
//        totalSpnListsSendData.setId("d2bd2f67-0b2c-4537-becc-d6bc0bb22ad4");


        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<TotalSpnLists> call = apiService.getMainSpnLists(totalSpnListsSendData);
        call.enqueue(new Callback<TotalSpnLists>() {
            @Override
            public void onResponse(Call<TotalSpnLists> call, Response<TotalSpnLists> response) {
                if (response.code() == 200) {
                    App.totalSpnLists = response.body();
                    presenter.responseResult(1);
                } else {
                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show();
                    presenter.responseResult(-4);
                }
            }

            @Override
            public void onFailure(Call<TotalSpnLists> call, Throwable t) {
//                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.responseResult(-5);
            }
        });

    }


    @Override
    public void requestSpnSubBrand(String spnBrandId) {

        SubBrandListSendData subBrandListSendData = new SubBrandListSendData();
        subBrandListSendData.setId(spnBrandId);
//        subBrandListSendData.setId("0595e679-d71a-40f5-a19e-19c7e89ba60c");


        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<SubBrandList> call = apiService.getSubBrandList(subBrandListSendData);
        call.enqueue(new Callback<SubBrandList>() {
            @Override
            public void onResponse(Call<SubBrandList> call, Response<SubBrandList> response) {
                if (response.code() == 200) {
                    App.subBrandList = response.body();
                    presenter.responseResultSpnSubBrand(1);
                } else {
                    presenter.responseResultSpnSubBrand(-4);
                }
            }

            @Override
            public void onFailure(Call<SubBrandList> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.responseResultSpnSubBrand(-5);
            }
        });

    }

    @Override
    public void requestSpnSubCategory(String spnCategoryId) {

        SubCategoryList2SendData subCategoryList2SendData = new SubCategoryList2SendData();
        subCategoryList2SendData.setId(spnCategoryId);

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<SubCategoryList2> call = apiService.getSubCategoryList2(subCategoryList2SendData);
        call.enqueue(new Callback<SubCategoryList2>() {
            @Override
            public void onResponse(Call<SubCategoryList2> call, Response<SubCategoryList2> response) {
                if (response.code() == 200) {
                    App.subCategoryList2 = response.body();
                    presenter.responseResultSpnSubCategoryList2(1);
                } else {
                    presenter.responseResultSpnSubCategoryList2(-4);
                }
            }

            @Override
            public void onFailure(Call<SubCategoryList2> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.responseResultSpnSubCategoryList2(-5);
            }
        });


    }

    @Override
    public void requestRegisterProduct(String edtCategory, String edtSubCategoty, String edtOwner, String edtBrand, String edtSubBrand,
                                       int spnCategoryPosition, int spnSubCategoryPosition, int spnOwnerPosition, int spnBrandPosition, int spnSubBrandPosition) {

        ProductRegisterSend_SendData sendData = new ProductRegisterSend_SendData();
        sendData.setId(App.idSpnFamily);
        sendData.setShop_id(App.idSpnShop);

        if (spnCategoryPosition != 0) {
            sendData.setCategory_id(App.totalSpnLists.data.subCategory.get(spnCategoryPosition).id);
        }

        if (spnOwnerPosition != 0) {
            sendData.setOwner_id(App.totalSpnLists.data.owner.get(spnOwnerPosition).id);
        }

        if (spnBrandPosition != 0) {
            sendData.setBrand_id(App.totalSpnLists.data.brand.get(spnBrandPosition).id);
        }


        sendData.setNew_category(edtCategory);


        if (spnSubCategoryPosition == 0 || spnSubCategoryPosition == -1) {
//
        } else {
            sendData.setSub_category_id(App.subCategoryList2.data.get(spnSubCategoryPosition).id);
        }

        sendData.setNew_sub_category(edtSubCategoty);
        sendData.setNew_owner(edtOwner);

        if (spnSubBrandPosition == 0 || spnSubBrandPosition == -1) {
//
        } else {
            sendData.setSub_brand_id(App.subBrandList.data.get(spnSubBrandPosition).id);
        }

        sendData.setNew_sub_brand(edtSubBrand);
        sendData.setNew_brand(edtBrand);

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<ProductRegisterSend> call = apiService.getProductRegisterSend(sendData);
        call.enqueue(new Callback<ProductRegisterSend>() {
            @Override
            public void onResponse(Call<ProductRegisterSend> call, Response<ProductRegisterSend> response) {
                if (response.code() == 200) {
                    App.productId = response.body().data;


                    presenter.productRegisterSendResult(1);
                    context.startActivity(new Intent(context, ProductRegisterDetailActivity.class));
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    presenter.productRegisterSendResult(-4);
                }
            }

            @Override
            public void onFailure(Call<ProductRegisterSend> call, Throwable t) {
                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.productRegisterSendResult(-5);
            }
        });
    }
}
