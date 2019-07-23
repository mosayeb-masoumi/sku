package com.example.sku.activities.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sku.R;
import com.example.sku.helpers.PersianAppcompatActivity;

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
    @BindView(R.id.btn_registerCode)
    Button btnRegisterCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        context = this;
        presenter.attachView(context, this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setTitle("داشبورد");

//        txtToolbarMain.setText(App.loginResult.result.getEmail());
//        presenter.loadView();


        btnNewFamily.setOnClickListener(v -> {
            inflateNewFamily();
        });
        btnNewShop.setOnClickListener(v -> {
            presenter.btnNewShopPressed();
        });
        btnFamily.setOnClickListener(v -> {
            inflateChooseFamily();
        });
        btnShop.setOnClickListener(v -> {
            inflateChooseShop();
        });

    }




    private void inflateChooseFamily() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.inflate_choose_family);
        dialog.setTitle("Title...");

        Spinner spnFamily = dialog.findViewById(R.id.spinnerChooseFamily);
        Button btnFamily = dialog.findViewById(R.id.btRegisterChooseFamily);
        ProgressBar pbFamily = dialog.findViewById(R.id.pbRegisterChooseFamily);




        dialog.show();
    }


    private void inflateChooseShop() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.inflate_choose_shop);
        dialog.setTitle("Title...");

        Spinner spnShop = dialog.findViewById(R.id.spinnerChooseShop);
        Button btnShop = dialog.findViewById(R.id.btRegisterChooseShop);
        ProgressBar pbShop = dialog.findViewById(R.id.pbRegisterChooseShop);



        dialog.show();
    }

    private void inflateNewFamily() {
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


}
