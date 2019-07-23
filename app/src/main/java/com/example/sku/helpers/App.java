package com.example.sku.helpers;

import android.app.Application;
import android.content.Context;

import com.example.sku.models.login.LoginResult;
import com.example.sku.models.shop.ShopList;


public class App extends Application {

 public static String ServerURL = "https://sku.rahbarbazar.com/sku/api/v1/";

    public static Context context;

    public static LoginResult loginResult = new LoginResult();
    public static ShopList shopList = new ShopList();



    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
