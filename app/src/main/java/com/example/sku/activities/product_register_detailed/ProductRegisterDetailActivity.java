package com.example.sku.activities.product_register_detailed;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sku.R;
import com.example.sku.helpers.App;
import com.example.sku.helpers.PersianAppcompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductRegisterDetailActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;

    ParentAdapter adapter;

    @BindView(R.id.toolbar_product_register_detailed)
    Toolbar toolbarProductRegisterDetailed;
    @BindView(R.id.recyclerView_productRedisterDetailed)
    RecyclerView recyclerViewProductRedisterDetailed;
    @BindView(R.id.txt_categoryProduct)
    TextView txtCategoryProduct;
    @BindView(R.id.txt_shop)
    TextView txtShop;
    @BindView(R.id.txt_subCategory)
    TextView txtSubCategory;
    @BindView(R.id.txt_category)
    TextView txtCategory;
    @BindView(R.id.txt_subBrand)
    TextView txtSubBrand;
    @BindView(R.id.txt_brand)
    TextView txtBrand;
    @BindView(R.id.txt_owner)
    TextView txtOwner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_register_detail);
        ButterKnife.bind(this);
        context = this;
        presenter.attachView(context, this);

        presenter.viewLoaded();


    }

    @Override
    public void setRecyclerview() {
        recyclerViewProductRedisterDetailed.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new ParentAdapter(App.productRegisterDetailDataList, getApplicationContext(), (Presenter) presenter);
        recyclerViewProductRedisterDetailed.setAdapter(adapter);
    }

    @Override
    public void setDetailInfo() {
        txtShop.setText(App.productDetailInfoParent.data.getShop());
        txtCategoryProduct.setText(App.productDetailInfoParent.data.getCategory());
        txtCategory.setText(App.productDetailInfoParent.data.getSubCategory());
        txtSubCategory.setText(App.productDetailInfoParent.data.getSubCategory2());
        txtBrand.setText(App.productDetailInfoParent.data.getBrand());
        txtSubBrand.setText(App.productDetailInfoParent.data.getSubBrand());
        txtOwner.setText(App.productDetailInfoParent.data.getOwner());
    }

    @Override
    public void setSpinner(Spinner spinnerRowParent, int position) {
        List<String> listSpnDetail = new ArrayList<String>();

        for (int j = 0; j < App.productRegisterDetailDataList.data.get(position).option.size(); j++) {
            listSpnDetail.add(App.productRegisterDetailDataList.data.get(position).option.get(j).title);
        }


        ArrayAdapter<String> spnChooseShopAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listSpnDetail);
//            ArrayAdapter<String> spnChooseShopAdapter = new ArrayAdapter<String>(context, R.layout.spinner_text, listSpnDetail);
        spnChooseShopAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spinnerRowParent.setAdapter(spnChooseShopAdapter);


         spinnerRowParent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position3, long id) {

                // to get a list of id of selected spnItems
                int position2 =  spinnerRowParent.getSelectedItemPosition();
                String strSpnId = App.productRegisterDetailDataList.data.get(position).option.get(position2).id;

                // todo here we shoud add strSpnId in an list tehn finally send a list (array)of IDs to server

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
