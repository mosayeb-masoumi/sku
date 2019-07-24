package com.example.sku.activities.qrcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.sku.R;
import com.example.sku.helpers.PersianAppcompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QRCodeActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    public static String ResultScan = "";

    @BindView(R.id.edtQR)
    EditText edtQR;
    @BindView(R.id.toolbar_Scanner)
    Toolbar toolbarScanner;
    @BindView(R.id.btnScanner)
    Button btnScanner;
    @BindView(R.id.btRegisterScannerResult)
    Button btRegisterScannerResult;
    @BindView(R.id.pbRegister)
    ProgressBar pbRegister ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);
        context = this;
        presenter.attachView(context, this);


        setSupportActionBar(toolbarScanner);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setTitle("اسکنر");


        btnScanner.setOnClickListener(v -> startActivity(new Intent(QRCodeActivity.this, QRcodeScaner.class)));
        btRegisterScannerResult.setOnClickListener(v -> { presenter.btnRegisterPressed(edtQR.getText().toString());});

    }


    @Override
    public void onResume() {
        super.onResume();
        if (ResultScan != null && ResultScan.length() > 0) {
            edtQR.setText(ResultScan);
            ResultScan = "";
        }else{
            edtQR.setText(R.string.pleaseTypeBarcode);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void hideBtn() {
        btRegisterScannerResult.setVisibility(View.GONE);
        pbRegister.setVisibility(View.VISIBLE);
    }
}
