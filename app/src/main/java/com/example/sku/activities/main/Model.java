package com.example.sku.activities.main;

import android.content.Context;
import android.widget.Toast;

import com.example.sku.helpers.App;
import com.example.sku.helpers.Toaster;
import com.example.sku.models.shop.ShopList;
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
    public void requestShopList() {

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<ShopList> call = apiService.getShoplist("Bearer "+App.loginResult.result.getEmail());
//        Call<ShopList> call = apiService.getShoplist();
        call.enqueue(new Callback<ShopList>() {
            @Override
            public void onResponse(Call<ShopList> call, Response<ShopList> response) {
                if(response.code()==200){

                    App.shopList = response.body();
                    Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                    presenter.sopListResult(1);

                }else{
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                    presenter.sopListResult(-4);
                }
            }

            @Override
            public void onFailure(Call<ShopList> call, Throwable t) {

                Toast.makeText(context, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                presenter.sopListResult(-5);
            }
        });
    }

}
