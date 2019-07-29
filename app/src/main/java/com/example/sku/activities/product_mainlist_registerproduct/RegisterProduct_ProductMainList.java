package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sku.R;
import com.example.sku.helpers.PersianAppcompatActivity;

public class RegisterProduct_ProductMainList extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product__product_main_list);

        context = this;
        presenter.attachView(context, this);


        presenter.viewLoaded();
    }
}
