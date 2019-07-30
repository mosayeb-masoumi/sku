package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.Context;
import android.widget.Toast;

import com.example.sku.helpers.App;
import com.example.sku.models.product_register.TotalSpnLists;
import com.example.sku.models.product_register.TotalSpnListsSendData;
import com.example.sku.models.sub_brandList_spn.SubBrandList;
import com.example.sku.models.sub_brandList_spn.SubBrandListSendData;
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
    public void requestSpnLists() {

        TotalSpnListsSendData totalSpnListsSendData = new TotalSpnListsSendData();

        // todo remove hard code
//         totalSpnListsSendData.setId(App.idSpnFamily);
         totalSpnListsSendData.setId("d2bd2f67-0b2c-4537-becc-d6bc0bb22ad4");



        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<TotalSpnLists> call = apiService.getMainSpnLists(totalSpnListsSendData);
        call.enqueue(new Callback<TotalSpnLists>() {
            @Override
            public void onResponse(Call<TotalSpnLists> call, Response<TotalSpnLists> response) {
                if(response.code()==200){

                    App.totalSpnLists=response.body();
                    presenter.responseResult(1);
                }else{
                    Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();
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
    public void requestSpnSubBrand(int itemPositionBrand) {

        SubBrandListSendData subBrandListSendData= new SubBrandListSendData();
        //todo remove hard code
//        subBrandListSendData.setId(String.valueOf(itemPositionBrand));
        subBrandListSendData.setId("0595e679-d71a-40f5-a19e-19c7e89ba60c");

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<SubBrandList> call = apiService.getSubBrandList(subBrandListSendData);
        call.enqueue(new Callback<SubBrandList>() {
            @Override
            public void onResponse(Call<SubBrandList> call, Response<SubBrandList> response) {
                if(response.code()==200){
                    Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubBrandList> call, Throwable t) {
                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
