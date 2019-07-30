package com.example.sku.services;

import com.example.sku.models.barcode_check.BarcodeProductsList;
import com.example.sku.models.barcode_check.BarcodeSendData;
import com.example.sku.models.category.CategoryList;
import com.example.sku.models.city.CityList;
import com.example.sku.models.city.CitySendData;
import com.example.sku.models.login.LoginResult;
import com.example.sku.models.login.LoginSendData;
import com.example.sku.models.product_register.TotalSpnLists;
import com.example.sku.models.product_register.TotalSpnListsSendData;
import com.example.sku.models.province.ProvinceList;
import com.example.sku.models.register_shop.RegisterShop;
import com.example.sku.models.register_shop.RegisterShopSendData;
import com.example.sku.models.shop.ShopList;
import com.example.sku.models.sub_brandList_spn.SubBrandList;
import com.example.sku.models.sub_brandList_spn.SubBrandListSendData;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @POST("Login")
    Call<LoginResult> getLogin(@Body LoginSendData sendData);


    //    @Headers({"Accept: application/json"})
    @GET("Shop")
    Call<ShopList> getShoplist(@Header("Authorization") String BearerToken);

    //mes
//    @Headers({"Authorization: Bearer user1@sku.com"})
//    @GET("Shop")
//    Call<ShopList> getShoplist();


    @Headers({"Authorization: Bearer user1@sku.com"})
    @GET("Shop/Province")
    Call<ProvinceList> getProvinceList();


    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Shop/City")
    Call<CityList> getCityList(@Body CitySendData citySendData);


    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Shop/Create")
    Call<RegisterShop> getRegisterShop(@Body RegisterShopSendData registerShopSendData);


    @Headers({"Authorization: Bearer user1@sku.com"})
    @GET("Category")
    Call<CategoryList> getCategoryList();


    //    @Headers({"Authorization: Bearer user1@sku.com","Accept: application/json"})
    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Barcode/Check")
    Call<BarcodeProductsList> getBarcodeProductList(@Body BarcodeSendData barcodeSendData);

    //    @Headers({"Authorization: Bearer user1@sku.com", "Accept: application/json"})
    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Product/MainList")
    Call<TotalSpnLists> getMainSpnLists(@Body TotalSpnListsSendData totalSpnListsSendData);


    @Headers({"Authorization: Bearer user1@sku.com"})
    @POST("Product/SubBrandList")
    Call<SubBrandList> getSubBrandList(@Body SubBrandListSendData subBrandListSendData);

}
