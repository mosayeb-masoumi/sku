package com.example.sku.activities.barcode_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.sku.R;
import com.example.sku.helpers.App;
import com.example.sku.helpers.PersianAppcompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_list);
        ButterKnife.bind(this);
        context = this;
        presenter.attachView(context, this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new AdapterBarcodelist(App.barcodeProductsList, getApplicationContext());
        recyclerView.setAdapter(adapter);


        btnProductRegister.setOnClickListener(v -> {
            presenter.btnProductRegisterPressed();
        });

        btnBarcodeRegister.setOnClickListener(v -> {
            presenter.btnBarcodeRegisterPressed();
        });
    }

    @Override
    public void hideBtn() {
        btnProductRegister.setVisibility(View.GONE);
        pbProductRegister.setVisibility(View.VISIBLE);
    }
}
