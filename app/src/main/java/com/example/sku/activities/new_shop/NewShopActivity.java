package com.example.sku.activities.new_shop;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.sku.R;
import com.example.sku.helpers.PersianAppcompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewShopActivity extends PersianAppcompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.spinnerProvince)
    Spinner spinnerProvince;
    @BindView(R.id.spinnercity)
    Spinner spinnercity;
    @BindView(R.id.spinnerArea)
    Spinner spinnerArea;
    @BindView(R.id.btRegister)
    Button btRegister;
    @BindView(R.id.pbRegister)
    ProgressBar pbRegister;
    @BindView(R.id.rlButtons)
    RelativeLayout rlButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shop);
        ButterKnife.bind(this);


    }
}
