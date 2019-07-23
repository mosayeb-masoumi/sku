package com.example.sku.activities.splash;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sku.R;
import com.example.sku.helpers.PersianAppcompatActivity;

public class SplashActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        context = this;
        presenter.attachView(context, this);
        presenter.activityLoaded();
    }
}
