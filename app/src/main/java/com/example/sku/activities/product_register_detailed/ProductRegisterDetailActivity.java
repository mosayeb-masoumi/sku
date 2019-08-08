package com.example.sku.activities.product_register_detailed;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sku.R;
import com.example.sku.activities.product_register_detailed.database.AppDatabase;
import com.example.sku.activities.product_register_detailed.database.MyModelSaveDB;
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
    @BindView(R.id.btnRegisterDetail)
    Button btnRegisterDetail;
    @BindView(R.id.pbRegisterDetail)
    ProgressBar pbRegisterDetail;


    AppDatabase db;
    List<MyModelSaveDB> myModelSaveDBS;


    //
//    String strSpnId;
    ArrayList<Integer> listChildPosition = new ArrayList<>();
    ArrayList<Integer> listParentPosition = new ArrayList<>();

    ArrayList<String> listSpnId = new ArrayList<>();
    ArrayList<String> mainListSpnId = new ArrayList<>();
    Spinner spinner;

    List<SendDataId> myModelSavesArray = new ArrayList<SendDataId>();
    SendDataId mModel;
    List<Integer> poslist = new ArrayList<>();

    int firstCheck = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_register_detail);
        ButterKnife.bind(this);
        context = this;
        presenter.attachView(context, this);

        presenter.viewLoaded();


         db = Room.databaseBuilder(context,AppDatabase.class,"detail")
                .allowMainThreadQueries()
                .build();

        db.detailDAO().deleteAll();


        btnRegisterDetail.setOnClickListener(v -> {

           
            List<MyModelSaveDB>listdata=new ArrayList<>();
            listdata=db.detailDAO().getAllMyModelSaveDB();
            List<SendDataId>senddata=new ArrayList<>();
            for (MyModelSaveDB m:listdata){
                senddata.add(new SendDataId(m.getValue()));
            }
            Toast.makeText(context, "dfsdfdsf", Toast.LENGTH_SHORT).show();

        });
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





        // to insert data in spinners
        for (int j = 0; j < App.productRegisterDetailDataList.data.get(position).option.size(); j++) {
            listSpnDetail.add(App.productRegisterDetailDataList.data.get(position).option.get(j).title);
        }


        ArrayAdapter<String> spnChooseShopAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listSpnDetail);
//            ArrayAdapter<String> spnChooseShopAdapter = new ArrayAdapter<String>(context, R.layout.spinner_text, listSpnDetail);
        spnChooseShopAdapter.setDropDownViewResource(android.R.layout.simple_list_item_activated_1);
        spinnerRowParent.setAdapter(spnChooseShopAdapter);

        spinner = spinnerRowParent;

        spinnerRowParent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position3, long id) {


                db = Room.databaseBuilder(context,AppDatabase.class,"detail")
                        .allowMainThreadQueries()
                        .build();

                // to get a list of id of selected spnItems

                int position2 = spinnerRowParent.getSelectedItemPosition();

                if (firstCheck <= position) {
//                    myModelSavesArray.add(new SendDataId(App.productRegisterDetailDataList.data.get(position).option.get(position2).id, position));

                    MyModelSaveDB myModelSaveDB = new MyModelSaveDB(position,
                            App.productRegisterDetailDataList.data.get(position).option.get(position2).id);

                    db.detailDAO().insertAll(myModelSaveDB);

                }


                if(firstCheck>position){
                    for (MyModelSaveDB model:db.detailDAO().getAllMyModelSaveDB())
                        if (model.getSpnId()==position) {
                            db.detailDAO().deleteItem(model);
                            MyModelSaveDB myModelSaveDB = new MyModelSaveDB(position,
                                    App.productRegisterDetailDataList.data.get(position).option.get(position2).id);
                            db.detailDAO().insertAll(myModelSaveDB);
                        }
                }

                firstCheck++;



//                if(db.detailDAO().getAllMyModelSaveDB().get(position).getSpnId() == position){

//                    db.detailDAO().deleteItem(myModelSaveDBS.get(position));
//                    MyModelSaveDB myModelSaveDB = new MyModelSaveDB(position,
//                            App.productRegisterDetailDataList.data.get(position).option.get(position2).id);
//
//                    db.detailDAO().insertAll(myModelSaveDB);

//                }else{
//                    MyModelSaveDB myModelSaveDB = new MyModelSaveDB(position,
//                            App.productRegisterDetailDataList.data.get(position).option.get(position2).id);
//
//                    db.detailDAO().insertAll(myModelSaveDB);
//                }

//                if (myModelSavesArray.size() > 0 && myModelSavesArray != null) {
//                    for (MyModelSaveDB m : db.detailDAO().getAllMyModelSaveDB()) {
//                        if (m.getSpnId() == position) {
//
//                            db.detailDAO().deleteItem(myModelSaveDBS.get(position));
//
//                            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, "el", Toast.LENGTH_SHORT).show();
//                        }
//                    }

//                    ListIterator<SendDataId> iter = myModelSavesArray.listIterator();
//                    while(iter.hasNext()){
//                        if(iter.next().posId==position){
//                            iter.remove();
//                            String value=App.productRegisterDetailDataList.data.get(position).option.get(position2).id;
//                            myModelSavesArray.add(new SendDataId(value, position));
//                        }
//                    }
//                    for (SendDataId m : myModelSavesArray) {
//                        if (m.posId == position) {
//                            myModelSavesArray.remove(m);
//                            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, "el", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    Toast.makeText(context, "asdsad", Toast.LENGTH_SHORT).show();
//                } else {
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
