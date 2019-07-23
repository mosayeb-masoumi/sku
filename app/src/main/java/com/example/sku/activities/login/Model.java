package com.example.sku.activities.login;

import android.content.Context;
import android.widget.Toast;

import com.example.sku.helpers.App;
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

    @Override
    public void attachPresenter(Contract.Presenter presenter , Context context) {
        this.presenter = presenter;
        this.context=context;
    }

    @Override
    public void requestLogin(String email, String password) {


        LoginSendData senddata = new LoginSendData();
        senddata.email=email;
        senddata.password=password;



        APIService apiService = APIClient.getClient().create(APIService.class);
       Call<LoginResult> call = apiService.getLogin(senddata);
       call.enqueue(new Callback<LoginResult>() {
           @Override
           public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

               if (response.code()==200){

                    presenter.loginResult(1);
                   App.loginResult = response.body();

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
               presenter.loginResult(-5);
           }
       });



    }
}
