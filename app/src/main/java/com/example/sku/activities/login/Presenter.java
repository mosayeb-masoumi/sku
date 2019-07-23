package com.example.sku.activities.login;

import android.content.Context;
import android.content.Intent;

import com.example.sku.activities.main.MainActivity;

public class Presenter implements Contract.Presenter {
    private Context context;
    private Contract.View view;
    private Contract.Model model = new Model();

    @Override
    public void attachView(Context context, Contract.View view) {
        this.view = view;
        this.context = context;
        model.attachPresenter(this,context);
    }

    @Override
    public void btLoginClicked(String email, String password) {

        if(email.equals(""))
            view.showErrorEmpetyEmail();
        else if(password.equals(""))
            view.showEmpetyPassword();
        else{
            view.hideBtnLogin();
            model.requestLogin(email , password);
        }
    }


    @Override
    public void loginResult(int result) {

        if(result == 1 ){
            context.startActivity(new Intent(context, MainActivity.class));
        }else if(result== -4){

        }else if(result == -5){

        }


    }


}
