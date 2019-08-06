package com.example.sku.activities.product_register_detailed;

import android.content.Context;

import com.example.sku.helpers.App;
import com.example.sku.models.product_register_detail.ProductDetailInfoParent;
import com.example.sku.models.product_register_detail.ProductRegisterDetailDataList;
import com.example.sku.models.product_register_detail.ProductRegisterDetail_SendData;
import com.example.sku.services.APIClient;
import com.example.sku.services.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sku.helpers.App.productId;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }


    @Override
    public void viewLoade() {

        ProductRegisterDetail_SendData sendData = new ProductRegisterDetail_SendData();

        //todo reinstead with dynamic data
//        sendData.setId(App.productId);
        sendData.setId("34b6cae58fbc4f57bcd7298dead76349");

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<ProductRegisterDetailDataList> call = apiService.getProductRegisterDetailDatalist(sendData);
        call.enqueue(new Callback<ProductRegisterDetailDataList>() {
            @Override
            public void onResponse(Call<ProductRegisterDetailDataList> call, Response<ProductRegisterDetailDataList> response) {
                if (response.code() == 200) {

                    App.productRegisterDetailDataList = response.body();
                    String productId = sendData.getId();
                    presenter.productRegisterDetailDataListResult(1, productId);
                } else {
                    presenter.productRegisterDetailDataListResult(-4, productId);
                }
            }

            @Override
            public void onFailure(Call<ProductRegisterDetailDataList> call, Throwable t) {
                presenter.productRegisterDetailDataListResult(-5, productId);
            }
        });


    }

    @Override
    public void requestRegisterDetailInfo(String productId) {

        ProductRegisterDetail_SendData sendData = new ProductRegisterDetail_SendData();

        //todo reinstead with dynamic data
//        sendData.setId(App.productId); or
//        sendData.setId(productId);
        sendData.setId("34b6cae58fbc4f57bcd7298dead76349");


        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<ProductDetailInfoParent> call = apiService.getProductDetailInfoParent(sendData);
        call.enqueue(new Callback<ProductDetailInfoParent>() {
            @Override
            public void onResponse(Call<ProductDetailInfoParent> call, Response<ProductDetailInfoParent> response) {
                if (response.code() == 200) {
                    App.productDetailInfoParent = response.body();
                    presenter.productDetailInfoResult(1);
                } else {
                    presenter.productDetailInfoResult(-4);
                }
            }

            @Override
            public void onFailure(Call<ProductDetailInfoParent> call, Throwable t) {
                presenter.productDetailInfoResult(-5);
            }
        });


    }
}
