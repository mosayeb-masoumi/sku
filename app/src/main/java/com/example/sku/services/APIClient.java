package com.example.sku.services;

import com.example.sku.helpers.App;
import com.example.sku.helpers.Toaster;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            try {


                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();


                retrofit = new Retrofit.Builder()
                        .baseUrl(App.ServerURL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
            } catch (Exception e) {
                Toaster.shorter("آدرس سرور اشتباه است");
            }

        }
        return retrofit;
    }
}
