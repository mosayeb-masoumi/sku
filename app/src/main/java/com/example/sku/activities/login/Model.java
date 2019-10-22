package com.example.sku.activities.login;

import android.content.Context;
import android.widget.Toast;

import com.example.sku.helpers.App;
import com.example.sku.helpers.Cache;
import com.example.sku.helpers.GpsTracker;
import com.example.sku.helpers.Toaster;
import com.example.sku.helpers.api_error.APIError;
import com.example.sku.helpers.api_error.ErrorUtils;
import com.example.sku.models.login.LoginResult;
import com.example.sku.models.login.LoginSendData;
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
    private GpsTracker gpsTracker;
    String strLat,strLng;

    @Override
    public void attachPresenter(Contract.Presenter presenter , Context context) {
        this.presenter = presenter;
        this.context=context;
    }

    @Override
    public void requestLogin(String email, String password) {

        getLocation();

        LoginSendData senddata = new LoginSendData();
        senddata.email=email;
        senddata.password=password;
        senddata.lat = strLat;
        senddata.lng = strLng;


        Service service = new ServiceProvider(context).getmService();
        Call<LoginResult> call = service.getLogin(senddata);


       call.enqueue(new Callback<LoginResult>() {
           @Override
           public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

               if (response.code()==200){

                   presenter.loginResult(1);
                   App.loginResult = response.body();
                   presenter.saveEmailPassword(response.body().result.email ,response.body().result.password);

               }else if(response.code()==422){

                   APIError apiError = ErrorUtils.parseError(response);
                   StringBuilder builderTitle = new StringBuilder();
                   for (String a : apiError.errors.email) {
                       builderTitle.append("" + a + " ");
                   }

                   StringBuilder builderPassword = new StringBuilder();
                   for (String b : apiError.errors.password) {
                       builderPassword.append("" + b + " ");
                   }

                   Toast.makeText(context, ""+builderTitle +"\n"+ builderPassword , Toast.LENGTH_LONG).show();
                   presenter.loginResult(422);
               } else  {
                   presenter.loginResult(-4);
               }
           }


           @Override
           public void onFailure(Call<LoginResult> call, Throwable t) {
               presenter.loginResult(-5);
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
    public void saveEmailPassword(String email, String password) {
        Cache.setString("email",email);
        Cache.setString("password",password);
    }
}
