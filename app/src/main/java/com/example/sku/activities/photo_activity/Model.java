package com.example.sku.activities.photo_activity;

import android.content.Context;
import android.widget.Toast;

import com.example.sku.helpers.App;
import com.example.sku.helpers.Toaster;
import com.example.sku.models.login.LoginResult;
import com.example.sku.models.send_pics.SendPics;
import com.example.sku.services.APIClient;
import com.example.sku.services.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;

    @Override
    public void attachPresenter(Contract.Presenter presenter , Context context) {
        this.presenter = presenter;
        this.context=context;
    }

    @Override
    public void requestSendPics(String strBm1, String strBm2, String strBm3, String strBm4) {


        SendPics sendPics = new SendPics();
        sendPics.setStrImg1(strBm1);
        sendPics.setStrImg2(strBm2);
        sendPics.setStrImg3(strBm3);
        sendPics.setStrImg4(strBm4);
        sendPics.setProductId(App.productId);

//        APIService apiService = APIClient.getClient().create(APIService.class);
//        Call<Boolean> call = apiService.send_pics(sendPics);
//        call.enqueue(new Callback<LoginResult>() {

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<Boolean> call = apiService.send_pics(sendPics);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.code() == 200){
                    presenter.sendpisResult(1);
                }else{
                    Toast.makeText(context, "server error", Toast.LENGTH_SHORT).show();
                    presenter.sendpisResult(-4);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(context, "connection error", Toast.LENGTH_SHORT).show();
                presenter.sendpisResult(-5);
            }
        });

    }
}
