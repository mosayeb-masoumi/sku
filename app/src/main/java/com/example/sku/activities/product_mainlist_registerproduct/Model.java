package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.sku.activities.product_register_detailed.ProductRegisterDetailActivity;
import com.example.sku.helpers.App;
import com.example.sku.models.category.CategoryList;
import com.example.sku.models.product_register.TotalSpnLists;
import com.example.sku.models.product_register.TotalSpnListsSendData;
import com.example.sku.models.product_register_send.ProductRegisterSend;
import com.example.sku.models.product_register_send.ProductRegisterSend_SendData;
import com.example.sku.models.sub_brandList_spn.SubBrandList;
import com.example.sku.models.sub_brandList_spn.SubBrandListSendData;
import com.example.sku.models.sub_categoryList_spn.SubCategoryList2;
import com.example.sku.models.sub_categoryList_spn.SubCategoryList2SendData;
import com.example.sku.network.Service;
import com.example.sku.network.ServiceProvider;
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

//        APIService apiService = APIClient.getClient().create(APIService.class);
//        Call<TotalSpnLists> call = apiService.getMainSpnLists(totalSpnListsSendData);


        Service service = new ServiceProvider(context).getmService();
        Call<TotalSpnLists> call = service.getMainSpnLists(totalSpnListsSendData);


        call.enqueue(new Callback<TotalSpnLists>() {
            @Override
            public void onResponse(Call<TotalSpnLists> call, Response<TotalSpnLists> response) {
                if (response.code() == 200) {
                    App.totalSpnLists = response.body();
                    presenter.responseResult(1);
                } else {
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

//        APIService apiService = APIClient.getClient().create(APIService.class);
//        Call<SubBrandList> call = apiService.getSubBrandList(subBrandListSendData);


        Service service = new ServiceProvider(context).getmService();
        Call<SubBrandList> call = service.getSubBrandList(subBrandListSendData);

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

//        APIService apiService = APIClient.getClient().create(APIService.class);
//        Call<SubCategoryList2> call = apiService.getSubCategoryList2(subCategoryList2SendData);

        Service service = new ServiceProvider(context).getmService();
        Call<SubCategoryList2> call = service.getSubCategoryList2(subCategoryList2SendData);


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
    public void requestRegisterProduct(String edtCategory, String edtSubCategoty, String edtProducer, String edtCompany_owner, String edtBrand,
                                       String edtSubBrand, String edtCost,
                                       int spnCategoryPosition, int spnSubCategoryPosition, int spnProducer_importerPosition,
                                       int spnOwner_companyPosition, int spnBrandPosition, int spnSubBrandPosition, int spnCountryPosition) {

        ProductRegisterSend_SendData sendData = new ProductRegisterSend_SendData();
        sendData.setId(App.idSpnFamily);
        sendData.setShop_id(App.idSpnShop);

        //////////////////////////////////////////
        if (spnCategoryPosition == 0) {
            sendData.setNew_category(edtCategory);
            sendData.setNew_sub_category(edtSubCategoty);
        }
        if(spnCategoryPosition!=0 && spnSubCategoryPosition == 0){
            sendData.setCategory_id(App.totalSpnLists.data.subCategory.get(spnCategoryPosition).id);
            sendData.setNew_sub_category(edtSubCategoty);
        }
        if(spnCategoryPosition!=0 && spnSubCategoryPosition != 0){
            sendData.setCategory_id(App.totalSpnLists.data.subCategory.get(spnCategoryPosition).id);
            sendData.setSub_category_id(App.subCategoryList2.data.get(spnSubCategoryPosition).id);
        }

        ////////////////////////////////////////////////


        if (spnProducer_importerPosition == 0) {
           sendData.setNew_owner(edtProducer);
        } else {
            sendData.setOwner_id(App.totalSpnLists.data.owner.get(spnProducer_importerPosition).id);
        }


        ////////////////////////
        if (spnOwner_companyPosition == 0) {
            sendData.setNew_company(edtCompany_owner);
        } else {
            sendData.setCompany_id(App.totalSpnLists.data.company.get(spnOwner_companyPosition).id);
        }

      /////////////////////////////

        if (spnBrandPosition == 0) {
            sendData.setNew_brand(edtBrand);
            sendData.setNew_sub_brand(edtSubBrand);
        }
        if(spnBrandPosition!=0 && spnSubBrandPosition == 0){
            sendData.setBrand_id(App.totalSpnLists.data.brand.get(spnBrandPosition).id);
            sendData.setNew_sub_brand(edtSubBrand);
        }
        if(spnBrandPosition!=0 && spnSubBrandPosition != 0){
            sendData.setBrand_id(App.totalSpnLists.data.brand.get(spnBrandPosition).id);
            sendData.setSub_brand_id(App.subBrandList.data.get(spnSubBrandPosition).id);
        }

//////////////////////////
        sendData.setCountry_id(App.totalSpnLists.data.country.get(spnCountryPosition).id);
        sendData.setPrice(edtCost);

        sendData.setBarcode(App.barcodeResult);


//        APIService apiService = APIClient.getClient().create(APIService.class);
//        Call<ProductRegisterSend> call = apiService.getProductRegisterSend(sendData);


        Service service = new ServiceProvider(context).getmService();
        Call<ProductRegisterSend> call = service.getProductRegisterSend(sendData);

        call.enqueue(new Callback<ProductRegisterSend>() {
            @Override
            public void onResponse(Call<ProductRegisterSend> call, Response<ProductRegisterSend> response) {
                if (response.code() == 200) {
                    App.productId = response.body().data;

                    presenter.productRegisterSendResult(1);
                    context.startActivity(new Intent(context, ProductRegisterDetailActivity.class));
                } else {
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
