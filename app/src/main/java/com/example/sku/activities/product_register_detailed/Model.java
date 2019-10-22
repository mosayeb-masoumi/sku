package com.example.sku.activities.product_register_detailed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.sku.activities.photo_activity.PhotoActivity;
import com.example.sku.helpers.App;
import com.example.sku.helpers.RxBus;
import com.example.sku.models.category.CategoryList;
import com.example.sku.models.product_register_detail.ProductDetailInfoParent;
import com.example.sku.models.product_register_detail.ProductRegisterDetailDataList;
import com.example.sku.models.product_register_detail.ProductRegisterDetail_SendData;
import com.example.sku.models.product_register_send.ProductRegisterSend;
import com.example.sku.network.Service;
import com.example.sku.network.ServiceProvider;
import com.example.sku.services.APIClient;
import com.example.sku.services.APIService;

import java.util.List;

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

        sendData.setId(App.productId);

//        APIService apiService = APIClient.getClient().create(APIService.class);
//        Call<ProductRegisterDetailDataList> call = apiService.getProductRegisterDetailDatalist(sendData);

        Service service = new ServiceProvider(context).getmService();
        Call<ProductRegisterDetailDataList> call = service.getProductRegisterDetailDatalist(sendData);

        call.enqueue(new Callback<ProductRegisterDetailDataList>() {
            @Override
            public void onResponse(Call<ProductRegisterDetailDataList> call, Response<ProductRegisterDetailDataList> response) {
                if (response.code() == 200) {

                    App.productRegisterDetailDataList = response.body();
                    String productId = sendData.getId();
                    presenter.productRegisterDetailDataListResult(1, productId);
                } else if(response.code()==204){
                    presenter.productRegisterDetailDataListResult(204, productId);
//                    Toast.makeText(context, "take pic", Toast.LENGTH_SHORT).show();

                }
                else {
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
    public void requestRegisterDetailInfo(String productId, boolean ok204) {

        ProductRegisterDetail_SendData sendData = new ProductRegisterDetail_SendData();

        //todo reinstead with dynamic data
//        sendData.setId(App.productId); or
        sendData.setId(productId);
;


//        APIService apiService = APIClient.getClient().create(APIService.class);
//        Call<ProductDetailInfoParent> call = apiService.getProductDetailInfoParent(sendData);

        Service service = new ServiceProvider(context).getmService();
        Call<ProductDetailInfoParent> call = service.getProductDetailInfoParent(sendData);

        call.enqueue(new Callback<ProductDetailInfoParent>() {
            @Override
            public void onResponse(Call<ProductDetailInfoParent> call, Response<ProductDetailInfoParent> response) {
                if (response.code() == 200) {
//                    App.productDetailInfoParent = response.body();
                    if(ok204){
                        RxBus.publish(response.body());
                        context.startActivity(new Intent(context,PhotoActivity.class));
                        ((Activity)context).finish();

                    }else{
                        presenter.setDetailInfoParent(response.body());
                        presenter.productDetailInfoResult(1);
                    }

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


    @Override

    public void requestSendList(List<EditTextContents> editTextContents, List<ModelSpinner> modelSpinners) {

        EditTextContentsList editTextContentsList = new EditTextContentsList();
        editTextContentsList.setEditTextContentsList(editTextContents);
        editTextContentsList.setModelSpinnerList(modelSpinners);
        editTextContentsList.setProductId(productId);

//
//        APIService apiService = APIClient.getClient().create(APIService.class);
//        Call<Boolean> call = apiService.get_option_create(editTextContentsList);


        Service service = new ServiceProvider(context).getmService();
        Call<Boolean> call = service.get_option_create(editTextContentsList);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                if(response.code()==200){
                    Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();

//                    boolean a = response.body().booleanValue();
                    context.startActivity(new Intent(context, PhotoActivity.class));


                }else{
                    Toast.makeText(context, "server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
