package com.example.sku.activities.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sku.R;
import com.example.sku.helpers.App;
import com.example.sku.helpers.PersianAppcompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    boolean doubleBackToExitPressedOnce = false;

    @BindView(R.id.txtToolbarMain)
    TextView txtToolbarMain;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_newShop)
    Button btnNewShop;
    @BindView(R.id.btnShop)
    Button btnShop;
    @BindView(R.id.btn_newFamily)
    Button btnNewFamily;
    @BindView(R.id.btn_family)
    Button btnFamily;
    @BindView(R.id.btn_registerBarCode)
    Button btnRegisterBarCode;
    @BindView(R.id.pb_family)
    ProgressBar pbFamily;


//    EditText edtQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        context = this;
        presenter.attachView(context, this);


//        edtQR=findViewById(R.id.edtQRcode);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setTitle("داشبورد");

        txtToolbarMain.setText(App.loginResult.result.getEmail());
        presenter.loadView();


        btnNewFamily.setOnClickListener(v -> {
            inflateNewCategory();
        });
        btnNewShop.setOnClickListener(v -> {
            presenter.btnNewShopPressed();
        });
        btnFamily.setOnClickListener(v -> {
            presenter.requestCategoryList();
        });
        btnShop.setOnClickListener(v -> {
            inflateChooseShop();
        });


        btnRegisterBarCode.setOnClickListener(v -> {
            presenter.btnRegiserCodePressed();
        });


    }


//    private void inflateChooseFamily() {
//        final Dialog dialog = new Dialog(context);
//        dialog.setContentView(R.layout.inflate_choose_family);
//        dialog.setTitle("Title...");
//
//        Spinner spnFamily = dialog.findViewById(R.id.spinnerChooseFamily);
//        Button btnFamily = dialog.findViewById(R.id.btRegisterChooseFamily);
//        ProgressBar pbFamily = dialog.findViewById(R.id.pbRegisterChooseFamily);
//
//
//
//
//        dialog.show();
//    }


    private void inflateChooseShop() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.inflate_choose_shop);
        dialog.setTitle("Title...");

        List<String> listSpnChooseShop = new ArrayList<String>();
        for (int i = 0; i < App.shopList.data.size(); i++) {
            listSpnChooseShop.add(App.shopList.data.get(i).name);
        }

        Spinner spnShop = dialog.findViewById(R.id.spinnerChooseShop);
        Button btnShop = dialog.findViewById(R.id.btRegisterChooseShop);
        ProgressBar pbShop = dialog.findViewById(R.id.pbRegisterChooseShop);

        ArrayAdapter<String> spnChooseShopAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listSpnChooseShop);
        spnChooseShopAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spnShop.setAdapter(spnChooseShopAdapter);


        dialog.show();
    }

    private void inflateNewCategory() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.inflate_alert_new_family);
        dialog.setTitle("Title...");


        dialog.show();
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            exitApp();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "برای خروج مجددا دکمه ی بازگشت را بزنید", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }


    private void exitApp() {
        finish();
        startActivity(new Intent(Intent.ACTION_MAIN).
                addCategory(Intent.CATEGORY_HOME).
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        }
        Process.killProcess(Process.myPid());
        super.finish();
    }


    @Override
    public void setFamilySpinner() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.inflate_choose_family);
        dialog.setTitle("Title...");

        Spinner spnFamily = dialog.findViewById(R.id.spinnerChooseFamily);
        Button btnFamily = dialog.findViewById(R.id.btRegisterChooseFamily);
        ProgressBar pbFamily = dialog.findViewById(R.id.pbRegisterChooseFamily);

        List<String> categoryList = new ArrayList<>();
        for (int i = 0; i < App.categoryList.data.size(); i++) {
            categoryList.add(App.categoryList.data.get(i).title);
        }

        ArrayAdapter<String> spnCityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryList);
        spnCityAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spnFamily.setAdapter(spnCityAdapter);


        dialog.show();
    }

    @Override
    public void hideBtnFamily() {
        btnFamily.setVisibility(View.GONE);
        pbFamily.setVisibility(View.VISIBLE);
    }

    @Override
    public void showBtnFamily() {
        btnFamily.setVisibility(View.VISIBLE);
        pbFamily.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "نیاز به اجازه ی دسترسی دوربین", Toast.LENGTH_SHORT).show();
                }

        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if (ResultScan != null && ResultScan.length() > 0) {
//            edtQR.setText(ResultScan);
//            ResultScan = "";
//        }
//    }
}
