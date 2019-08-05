package com.example.sku.activities.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

import com.example.sku.activities.main.MainActivity;
import com.example.sku.helpers.App;
import com.example.sku.helpers.Cache;
import com.example.sku.helpers.GpsTracker;
import com.example.sku.helpers.Toaster;
import com.example.sku.models.login.LoginResult;
import com.example.sku.models.login.LoginSendData;
import com.example.sku.services.APIClient;
import com.example.sku.services.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Model implements Contract.Model {

    private Contract.Presenter presenter;
    private Context context;
    private GpsTracker gpsTracker;
    String strLat,strLng;

    @Override
    public void attachPresenter(Contract.Presenter presenter ,Context context) {
        this.presenter = presenter;
        this.context=context;
    }

    @Override
    public void requestLogin() {
        getLocation();
        LoginSendData senddata = new LoginSendData();
        senddata.email= Cache.getString("email");
        senddata.password=Cache.getString("password");
        senddata.lat = strLat;
        senddata.lng = strLng;

        APIService apiService = APIClient.getClient().create(APIService.class);
        Call<LoginResult> call = apiService.getLogin(senddata);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                if (response.code()==200){
//                    presenter.loginResult(1);
                    App.loginResult = response.body();
                    context.startActivity(new Intent(context, MainActivity.class));
//                    presenter.saveEmailPassword(response.body().result.email ,response.body().result.password);

                }else {

                    if (response.code() == 403){
                        Toaster.shorter("رمز صحیح نمی باشد");
                    }else{
                        Toaster.shorter("خطا در ارتباط با سرور");
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
//               Toast.makeText(context, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                String error1 = "java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $";
                if(t.getMessage().toString().equals(error1))
                    Toaster.shorter("The selected email is invalid.");
//                presenter.loginResult(-5);
            }
        });


    }

    public void getLocation(){
        gpsTracker = new GpsTracker(context);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            strLat = (String.valueOf(latitude));
            strLng = (String.valueOf(longitude));
        }else{
            gpsTracker.showSettingsAlert();
        }
    }
    @Override
    public boolean checkGpsON() {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        }
        return true;
    }

}
