package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.sku.R;
import com.example.sku.helpers.App;
import com.example.sku.helpers.PersianAppcompatActivity;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterProduct_ProductMainList extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    @BindView(R.id.btnReister)
    Button btnReister;
    @BindView(R.id.SpnOwner)
    Spinner SpnOwner;
    @BindView(R.id.rlSpnOwner)
    RelativeLayout rlSpnOwner;
    @BindView(R.id.spnBrand)
    Spinner spnBrand;
    @BindView(R.id.rlSpnBrand)
    RelativeLayout rlSpnBrand;
    @BindView(R.id.spnSubBrand)
    Spinner spnSubBrand;
    @BindView(R.id.rlSpnSubBrand)
    RelativeLayout rlSpnSubBrand;
    @BindView(R.id.llSpnSubBrand)
    LinearLayout llSpnSubBrand;
    @BindView(R.id.spnCategoty)
    Spinner spnCategoty;
    @BindView(R.id.rlSpnCategory)
    RelativeLayout rlSpnCategory;
    @BindView(R.id.spnSubCategory)
    Spinner spnSubCategory;
    @BindView(R.id.rlSpnSubCategory)
    RelativeLayout rlSpnSubCategory;
    @BindView(R.id.llSpnSubCategory)
    LinearLayout llSpnSubCategory;
    @BindView(R.id.etAddCategory)
    EditText etAddCategory;
    @BindView(R.id.etAddOwner)
    EditText etAddOwner;
    @BindView(R.id.etAddBrand)
    EditText etAddBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product__product_main_list);
        ButterKnife.bind(this);

        context = this;
        presenter.attachView(context, this);

        presenter.viewLoaded();
    }


    @Override
    public void hideLoading() {
        stopAnim();
    }

    @Override
    public void showLoading() {
        startAnim();
    }


    @Override
    public void setSpnOwner() {

        List<String> ownerList = new ArrayList<>();
        for (int i = 0; i < App.totalSpnLists.data.owner.size(); i++) {
            ownerList.add(App.totalSpnLists.data.owner.get(i).title);
        }

        ArrayAdapter<String> spnCityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ownerList);
        spnCityAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        SpnOwner.setAdapter(spnCityAdapter);

        SpnOwner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    etAddOwner.setVisibility(View.VISIBLE);
                } else {
                    etAddOwner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void setSpnBrand() {
        List<String> brandList = new ArrayList<>();
        for (int i = 0; i < App.totalSpnLists.data.brand.size(); i++) {
            brandList.add(App.totalSpnLists.data.brand.get(i).title);
        }

        ArrayAdapter<String> spnCityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, brandList);
        spnCityAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spnBrand.setAdapter(spnCityAdapter);

        spnBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startAnim();
                if (position == 0) {
                    llSpnSubBrand.setVisibility(View.GONE);
                    etAddBrand.setVisibility(View.VISIBLE);
                } else {
                    llSpnSubBrand.setVisibility(View.VISIBLE);
                    etAddBrand.setVisibility(View.GONE);
                    int itemPositionBrand = spnBrand.getSelectedItemPosition();
                    presenter.requestDataSpnSubBrand(itemPositionBrand);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void setSpnCategory() {

        List<String> subCategoryList = new ArrayList<>();
        for (int i = 0; i < App.totalSpnLists.data.subCategory.size(); i++) {
            subCategoryList.add(App.totalSpnLists.data.subCategory.get(i).title);
        }

        ArrayAdapter<String> spnCityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subCategoryList);
        spnCityAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spnCategoty.setAdapter(spnCityAdapter);

        spnCategoty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startAnim();
                if (position == 0) {
                    llSpnSubCategory.setVisibility(View.GONE);
                    etAddCategory.setVisibility(View.VISIBLE);
                } else {
                    llSpnSubCategory.setVisibility(View.VISIBLE);
                    etAddCategory.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    void startAnim() {
        avi.show();
    }


    void stopAnim() {
        avi.hide();
    }

}
