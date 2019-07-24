package com.example.sku.activities.splash;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import com.example.sku.activities.login.LoginActivity;
import com.example.sku.helpers.Cache;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

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
        model.attachPresenter(this, context);
    }


    @Override
    public void activityLoaded() {

          String email = Cache.getString("email");
          String password = Cache.getString("password");

          if(!email.equals("") && !password.equals("")){
              model.requestLogin();

          }else{
              new Timer().schedule(new TimerTask() {
                  @Override
                  public void run() {
                      gotoLogin();
                  }
              }, 2700);
          }
    }

    @Override
    public void requestLogin() {
       model.requestLogin();
    }

    @Override
    public boolean checkGpsPermission() {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkGpsON() {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return false;
        }
        return true;
    }

    @Override
    public void getPermissionRequest() {
        ActivityCompat.requestPermissions((Activity) context
                ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},4);
    }




    private void gotoLogin() {
        context.startActivity(new Intent(context, LoginActivity.class));
        ((Activity) context).finish();
    }





}
