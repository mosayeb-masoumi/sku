package com.example.sku.helpers;

import android.app.Application;
import android.content.Context;

import com.example.sku.models.barcode_list.BarcodeProductsList;
import com.example.sku.models.category.CategoryList;
import com.example.sku.models.city.CityList;
import com.example.sku.models.login.LoginResult;
import com.example.sku.models.product_register.TotalSpnLists;
import com.example.sku.models.product_register_detail.ProductRegisterDetailDataList;
import com.example.sku.models.province.ProvinceList;
import com.example.sku.models.shop.ShopList;
import com.example.sku.models.sub_brandList_spn.SubBrandList;
import com.example.sku.models.sub_categoryList_spn.SubCategoryList2;


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

    public static SubBrandList subBrandList = new SubBrandList();
    public static SubCategoryList2 subCategoryList2 = new SubCategoryList2();

    public static ProductRegisterDetailDataList productRegisterDetailDataList = new ProductRegisterDetailDataList();


    public static String idSpnFamily = "";
    public static String idSpnShop = "";
    public static String barcodeResult = "";
    public static String productId = "";


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
