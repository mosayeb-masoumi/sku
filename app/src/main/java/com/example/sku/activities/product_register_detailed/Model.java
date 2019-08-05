package com.example.sku.activities.product_register_detailed;

import android.content.Context;
import android.widget.Toast;

import com.example.sku.helpers.App;
import com.example.sku.models.product_register_detail.ProductRegisterDetailDataList;
import com.example.sku.models.product_register_detail.ProductRegisterDetail_SendData;
import com.example.sku.services.APIClient;
import com.example.sku.services.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter ,Context context) {
        this.presenter = presenter;
        this.context=context;
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
                if(response.code()==200){

                    App.productRegisterDetailDataList=response.body();

                    Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductRegisterDetailDataList> call, Throwable t) {

                Toast.makeText(context, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
