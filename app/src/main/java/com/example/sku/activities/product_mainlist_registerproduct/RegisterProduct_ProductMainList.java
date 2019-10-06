package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.sku.R;
import com.example.sku.activities.photo_activity.PhotoActivity;
import com.example.sku.helpers.App;
import com.example.sku.helpers.GeneralTools;
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
    @BindView(R.id.toolbar_registerproduct)
    Toolbar toolbarRegisterproduct;
    @BindView(R.id.etAddSubCategory)
    EditText etAddSubCategory;
    @BindView(R.id.etAddSubBrand)
    EditText etAddSubBrand;
    @BindView(R.id.pbRegisterProducts)
    ProgressBar pbRegisterProducts;
    @BindView(R.id.SpnCompany)
    Spinner SpnCompany;
    @BindView(R.id.rlSpnCompany)
    RelativeLayout rlSpnCompany;
    @BindView(R.id.etAddCompany)
    EditText etAddCompany;
    @BindView(R.id.spnCountry)
    Spinner spnCountry;
    @BindView(R.id.rlSpnCountry)
    RelativeLayout rlSpnCountry;
    @BindView(R.id.etAddCost)
    EditText etAddCost;
    @BindView(R.id.rlButtons)
    RelativeLayout rlButtons;

    BroadcastReceiver connectivityReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product__product_main_list);
        ButterKnife.bind(this);

        context = this;
        presenter.attachView(context, this);

        //check network broadcast reciever
        GeneralTools tools = GeneralTools.getInstance();
        connectivityReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tools.doCheckNetwork(RegisterProduct_ProductMainList.this, findViewById(R.id.rl_root));
            }

        };

        presenter.viewLoaded();

        btnReister.setOnClickListener(v -> {

            String edtCategory = etAddCategory.getText().toString();
            String edtSubCategoty = etAddSubCategory.getText().toString();
            String edtOwner = etAddOwner.getText().toString();
            String edtBrand = etAddBrand.getText().toString();
            String edtSubBrand = etAddSubBrand.getText().toString();
            String edtProducer = etAddCompany.getText().toString();
            String edtCost = etAddCost.getText().toString();

            int spnCategoryPosition = spnCategoty.getSelectedItemPosition();
            int spnSubCategoryPosition = spnSubCategory.getSelectedItemPosition();
            int spnOwnerPosition = SpnOwner.getSelectedItemPosition();
            int spnBrandPosition = spnBrand.getSelectedItemPosition();
            int spnSubBrandPosition = spnSubBrand.getSelectedItemPosition();
            int spnCompanyPosition = SpnCompany.getSelectedItemPosition();
            int spnCountryPosition = spnCountry.getSelectedItemPosition();


            presenter.btnRegisterPressed(edtCategory, edtSubCategoty, edtOwner, edtBrand, edtSubBrand,edtProducer,edtCost,
                    spnCategoryPosition, spnSubCategoryPosition, spnOwnerPosition,
                    spnBrandPosition, spnSubBrandPosition,spnCompanyPosition,spnCountryPosition);

        });
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
                    stopAnim();
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
                    etAddSubBrand.setVisibility(View.VISIBLE);
                    stopAnim();
                } else {
//                    llSpnSubBrand.setVisibility(View.VISIBLE);
//                    etAddBrand.setVisibility(View.GONE);
                    int itemPositionBrand = spnBrand.getSelectedItemPosition();
                    presenter.requestDataSpnSubBrand(itemPositionBrand);
                    startAnim();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public void setSpnSubBrand() {

        llSpnSubBrand.setVisibility(View.VISIBLE);
        etAddBrand.setVisibility(View.GONE);
        etAddSubBrand.setVisibility(View.GONE);
        stopAnim();

        List<String> subBrandList = new ArrayList<>();
        for (int i = 0; i < App.subBrandList.data.size(); i++) {
            subBrandList.add(App.subBrandList.data.get(i).title);
        }
        ArrayAdapter<String> spnSubBrandAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subBrandList);
        spnSubBrandAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spnSubBrand.setAdapter(spnSubBrandAdapter);


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
                    etAddCategory.setVisibility(View.VISIBLE);
                    etAddSubCategory.setVisibility(View.VISIBLE);
                    llSpnSubCategory.setVisibility(View.GONE);
                    stopAnim();
                } else {
                    int itemPositionSpnCategory = spnCategoty.getSelectedItemPosition();
                    presenter.requestDataSpnSubCategory(itemPositionSpnCategory);

                    startAnim();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setSpnSubCategory() {
        stopAnim();
        llSpnSubCategory.setVisibility(View.VISIBLE);
        etAddCategory.setVisibility(View.GONE);
        etAddSubCategory.setVisibility(View.GONE);
        List<String> subCategoryList2 = new ArrayList<>();
        for (int i = 0; i < App.subCategoryList2.data.size(); i++) {
            subCategoryList2.add(App.subCategoryList2.data.get(i).title);
        }
        ArrayAdapter<String> spnSubCategoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subCategoryList2);
        spnSubCategoryAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spnSubCategory.setAdapter(spnSubCategoryAdapter);

    }


    @Override
    public void setSpnCountry() {
        stopAnim();
        List<String> countryList = new ArrayList<>();
        for (int i = 0; i < App.totalSpnLists.data.country.size(); i++) {
            countryList.add(App.totalSpnLists.data.country.get(i).title);
        }
        ArrayAdapter<String> spnCountryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryList);
        spnCountryAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spnCountry.setAdapter(spnCountryAdapter);
    }

    @Override
    public void setSpnCompany() {
        stopAnim();
        List<String> companyList = new ArrayList<>();
        for (int i = 0; i < App.totalSpnLists.data.company.size(); i++) {
            companyList.add(App.totalSpnLists.data.company.get(i).title);
        }
        ArrayAdapter<String> spnCompanyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, companyList);
        spnCompanyAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        SpnCompany.setAdapter(spnCompanyAdapter);

        SpnCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startAnim();
                if (position == 0) {
                    etAddCompany.setVisibility(View.VISIBLE);
                    stopAnim();
                } else {
                    etAddCompany.setVisibility(View.GONE);
                    stopAnim();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setErrorEdtCost(String strError) {
        etAddCost.setError(strError);
    }

    @Override
    public void setErrorEdtCompany(String strError) {
        etAddCompany.setError(strError);
    }


    @Override
    public void hideBtn() {
        btnReister.setVisibility(View.GONE);
        pbRegisterProducts.setVisibility(View.VISIBLE);
    }

    @Override
    public void setErrorEdtCategory(String strError) {
        etAddCategory.setError(strError);
    }

    @Override
    public void setErrorEdtSubCategory(String strError) {
        etAddSubCategory.setError(strError);
    }

    @Override
    public void setErrorEdtOwner(String strError) {
        etAddOwner.setError(strError);
    }

    @Override
    public void setErrorEdtBrand(String strError) {
        etAddBrand.setError(strError);
    }

    @Override
    public void setErrorEdtSubBrand(String strError) {
        etAddSubBrand.setError(strError);
    }

    @Override
    public void showBtn() {
        btnReister.setVisibility(View.VISIBLE);
        pbRegisterProducts.setVisibility(View.GONE);
    }



    void startAnim() {
        avi.show();
    }


    void stopAnim() {
        avi.hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(connectivityReceiver);
        super.onDestroy();
    }

}
