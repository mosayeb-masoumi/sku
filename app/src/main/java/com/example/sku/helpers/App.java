package com.example.sku.helpers;

import android.app.Application;
import android.content.Context;

import com.example.sku.models.barcode_check.BarcodeProductsList;
import com.example.sku.models.category.CategoryList;
import com.example.sku.models.city.CityList;
import com.example.sku.models.login.LoginResult;
import com.example.sku.models.province.ProvinceList;
import com.example.sku.models.shop.ShopList;


public class App extends Application {

// public static String ServerURL = "https://sku.rahbarbazar.com/sku/api/v1/";
 public static String ServerURL = "https://test.rahbarbazar.com/sku/api/v1/";

    public static Context context;

    public static LoginResult loginResult = new LoginResult();
    public static ShopList shopList = new ShopList();
    public static ProvinceList provinceList = new ProvinceList();
    public static CityList cityList = new CityList();
    public static CategoryList categoryList = new CategoryList();
    public static BarcodeProductsList barcodeProductsList = new BarcodeProductsList();



    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
