package com.example.sku.activities.barcode_list;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.sku.R;
import com.example.sku.helpers.GeneralTools;
import com.example.sku.helpers.PersianAppcompatActivity;
import com.example.sku.models.barcode_list.BarcodeProductsList;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BarcodeListActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    AdapterBarcodelist adapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.btn_product_register)
    Button btnProductRegister;
    @BindView(R.id.btn_barcode_register)
    Button btnBarcodeRegister;
    @BindView(R.id.pb_product_register)
    ProgressBar pbProductRegister;
    BroadcastReceiver connectivityReceiver = null;
    @BindView(R.id.avi_barcode_list)
    AVLoadingIndicatorView avi;
    @BindView(R.id.swpRefreshBarcodeList)
    SwipeRefreshLayout swpRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_list);
        ButterKnife.bind(this);
        context = this;
        presenter.attachView(context, this);

        presenter.viewLoaded();

        //check network broadcast reciever
        GeneralTools tools = GeneralTools.getInstance();
        connectivityReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tools.doCheckNetwork(BarcodeListActivity.this, findViewById(R.id.rl_root));
            }

        };


        btnProductRegister.setOnClickListener(v -> {
            presenter.btnProductRegisterPressed();
        });

        btnBarcodeRegister.setOnClickListener(v -> {
            presenter.btnBarcodeRegisterPressed();
        });

        swpRefresh.setOnRefreshListener(() -> presenter.viewLoaded());
    }

    @Override
    public void hideBtn() {
        btnProductRegister.setVisibility(View.GONE);
        pbProductRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void setRecyclerview(BarcodeProductsList barcodeProductsList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        adapter = new AdapterBarcodelist(App.barcodeProductsList, getApplicationContext());
        adapter = new AdapterBarcodelist(barcodeProductsList, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        avi.setVisibility(View.VISIBLE);
        swpRefresh.setRefreshing(false);
    }

    @Override
    public void stopLoading() {
        avi.setVisibility(View.GONE);
        swpRefresh.setRefreshing(false);
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
