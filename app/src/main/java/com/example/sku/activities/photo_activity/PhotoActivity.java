package com.example.sku.activities.photo_activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sku.R;
import com.example.sku.activities.main.MainActivity;
import com.example.sku.helpers.GeneralTools;
import com.example.sku.helpers.PersianAppcompatActivity;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.listeners.IPickResult;

public class PhotoActivity extends PersianAppcompatActivity implements IPickResult {

    BroadcastReceiver connectivityReceiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        //check network broadcast reciever
        GeneralTools tools = GeneralTools.getInstance();
        connectivityReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tools.doCheckNetwork(PhotoActivity.this, findViewById(R.id.rl_root));
            }

        };
    }











    @Override
    public void onPickResult(PickResult r) {

    }








    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(connectivityReceiver);
        super.onDestroy();
    }


}
