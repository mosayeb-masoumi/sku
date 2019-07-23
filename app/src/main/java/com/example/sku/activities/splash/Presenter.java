package com.example.sku.activities.splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.sku.activities.login.LoginActivity;
import com.example.sku.activities.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

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
    public void activityLoaded() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                gotoLogin();
            }
        }, 2700);
    }


    private void gotoLogin() {


        context.startActivity(new Intent(context, LoginActivity.class));
//        context.startActivity(new Intent(context, MainActivity.class));
        ((Activity) context).finish();

    }

}
