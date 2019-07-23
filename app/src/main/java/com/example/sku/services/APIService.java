package com.example.sku.services;

import com.example.sku.models.login.LoginResult;
import com.example.sku.models.login.LoginSendData;
import com.example.sku.models.shop.ShopList;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @POST("Login")
    Call<LoginResult> getLogin(@Body LoginSendData sendData);




    @Headers({"Accept: application/json"})
    @GET("Shop")
    Call<ShopList> getShoplist(@Header("Authorization") String BearerToken);

//    @Headers({"Authorization: Bearer user1@sku.com"})
//    @GET("Shop")
//    Call<ShopList> getShoplist();



}
