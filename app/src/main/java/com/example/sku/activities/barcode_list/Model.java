package com.example.sku.activities.barcode_list;

import android.content.Context;
import android.widget.Toast;

import com.example.sku.helpers.App;
import com.example.sku.models.barcode_check.BarcodeCheckSendData;
import com.example.sku.models.barcode_list.BarcodeProductsList;
import com.example.sku.models.barcode_list.BarcodeSendData;
import com.example.sku.models.category.CategoryList;
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
    public void attachPresenter(Contract.Presenter presenter , Context context) {
        this.presenter = presenter;
        this.context=context;
    }



    @Override
    public void requestBarcodeProductList() {

       BarcodeSendData barcodeSendData = new BarcodeSendData();
       barcodeSendData.setCode(App.barcodeResult);



//        APIService apiService = APIClient.getClient().create(APIService.class);
//        Call<BarcodeProductsList> call = apiService.getBarcodeProductList(barcodeSendData);


        Service service = new ServiceProvider(context).getmService();
        Call<BarcodeProductsList> call = service.getBarcodeProductList(barcodeSendData);


        call.enqueue(new Callback<BarcodeProductsList>() {
            @Override
            public void onResponse(Call<BarcodeProductsList> call, Response<BarcodeProductsList> response) {
                if(response.code()==200){

//                        App.barcodeProductsList = response.body();
                        presenter.setRecyclereView(response.body());
//                        presenter.barcodeProductsList(1);


                }else{
                    Toast.makeText(context, "serverError", Toast.LENGTH_SHORT).show();
                    presenter.barcodeProductsList(4);
                }
            }

            @Override
            public void onFailure(Call<BarcodeProductsList> call, Throwable t) {
                Toast.makeText(context, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                presenter.barcodeProductsList(5);
            }
        });
    }
}
