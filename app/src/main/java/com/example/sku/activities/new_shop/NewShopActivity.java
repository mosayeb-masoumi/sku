package com.example.sku.activities.new_shop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sku.R;
import com.example.sku.helpers.App;
import com.example.sku.helpers.PersianAppcompatActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewShopActivity extends PersianAppcompatActivity implements Contract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edtShopName)
    EditText edtShopName;
    @BindView(R.id.edtShopAddress)
    EditText edtShopAddress;
    @BindView(R.id.edtShopTel)
    EditText edtShopTel;
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
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;

    Contract.Presenter presenter = new Presenter();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shop);
        ButterKnife.bind(this);

        context = this;
        presenter.attachView(context, this);
        presenter.viewLoaded();
        presenter.loadDataSpinnerArea();

        btRegister.setOnClickListener(v -> {

            if (!edtShopName.getText().toString().equals("") && !edtShopAddress.getText().toString().equals("") &&
                    !edtShopTel.getText().toString().equals("") && (spinnerProvince.getSelectedItem().toString() != "")
                    && (spinnercity.getSelectedItem().toString() != "") && (spinnerArea.getSelectedItem().toString() != "")) {

                presenter.btnRegisterNewShopClicked(edtShopName.getText().toString(), edtShopAddress.getText().toString()
                        , edtShopTel.getText().toString(), spinnerProvince.getSelectedItemPosition(),
                        spinnercity.getSelectedItemPosition(), spinnerArea.getSelectedItemPosition());
            }else{
                Toast.makeText(context, "لطفا فیلد ها را پر نمایید", Toast.LENGTH_SHORT).show();
            }


        });


    }


    @Override
    public void setSpinnerArea(String[] spinnerAreaData) {
        ArrayAdapter<String> spinnerAreaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerAreaData);
        spinnerAreaAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spinnerArea.setAdapter(spinnerAreaAdapter);
    }

    @Override
    public void setSpinnerProvinve() {

        List<String> provinceList = new ArrayList<>();
        for (int i = 0; i < App.provinceList.data.size(); i++) {
            provinceList.add(App.provinceList.data.get(i).province);
        }
        ArrayAdapter<String> spnProvinceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provinceList);
        spnProvinceAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spinnerProvince.setAdapter(spnProvinceAdapter);


        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                startAnim();
                int positionProvince = spinnerProvince.getSelectedItemPosition();
                presenter.requestDataSpnCity(positionProvince);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setSpinnerCity() {
        List<String> cityList = new ArrayList<>();
        for (int i = 0; i < App.cityList.data.size(); i++) {
            cityList.add(App.cityList.data.get(i).city);
        }

        ArrayAdapter<String> spnCityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityList);
        spnCityAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spinnercity.setAdapter(spnCityAdapter);
    }

    @Override
    public void stopLoading() {
        stopAnim();
    }

    @Override
    public void startLoading() {
        startAnim();
    }

    @Override
    public void hideBtnRegister() {
        btRegister.setVisibility(View.GONE);
    }

    @Override
    public void showPbRegister() {
     pbRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePb() {
        pbRegister.setVisibility(View.GONE);
    }

    @Override
    public void showBtn() {
     btRegister.setVisibility(View.VISIBLE);
    }

    void startAnim() {
        avi.show();
    }


    void stopAnim() {
        avi.hide();
    }

}
