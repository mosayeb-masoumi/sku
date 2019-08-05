package com.example.sku.activities.product_register_detailed;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sku.R;

public class ProductRegisterDetailActivity extends AppCompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_register_detail);
        context = this;
        presenter.attachView(context, this);


        presenter.viewLoaded();
    }
}
