package com.example.sku.activities.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.sku.helpers.App;
import com.example.sku.helpers.Toaster;
import com.example.sku.models.category.CategoryList;
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


    @Override
    public void requestCategoryList() {

        APIService apiService=APIClient.getClient().create(APIService.class);
        Call<CategoryList> call = apiService.getCategoryList();
        call.enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                if(response.code()==200){
                    App.categoryList=response.body();
                    presenter.getCategoryListResult(1);
                }else{
                    Toast.makeText(context, "server error", Toast.LENGTH_SHORT).show();
                    presenter.getCategoryListResult(-4);
                }
            }

            @Override
            public void onFailure(Call<CategoryList> call, Throwable t) {

                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.getCategoryListResult(-5);
            }
        });

    }


    @Override
    public void requestPermissionCamera() {
        ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CAMERA},1);
    }

}
