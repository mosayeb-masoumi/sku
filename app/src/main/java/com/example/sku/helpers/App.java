package com.example.sku.helpers;

import android.app.Application;
import android.content.Context;

import com.example.sku.models.barcode_check.BarcodeProductsList;
import com.example.sku.models.category.CategoryList;
import com.example.sku.models.city.CityList;
import com.example.sku.models.login.LoginResult;
import com.example.sku.models.product_register.Data;
import com.example.sku.models.product_register.Owner;
import com.example.sku.models.product_register.TotalSpnLists;
import com.example.sku.models.province.ProvinceList;
import com.example.sku.models.shop.ShopList;

import java.util.Collections;
import java.util.List;


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
    public static TotalSpnLists totalSpnLists = new TotalSpnLists();

    public static String idSpnFamily ="";



    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
