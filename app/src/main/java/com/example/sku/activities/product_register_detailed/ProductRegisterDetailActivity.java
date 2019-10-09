package com.example.sku.activities.product_register_detailed;

import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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

import com.example.sku.R;
import com.example.sku.activities.photo_activity.PhotoActivity;
import com.example.sku.activities.product_register_detailed.database.AppDatabase;
import com.example.sku.activities.product_register_detailed.database.MyModelSaveDB;
import com.example.sku.helpers.App;
import com.example.sku.helpers.GeneralTools;
import com.example.sku.helpers.PersianAppcompatActivity;
import com.example.sku.models.product_register_detail.ModelTest;
import com.example.sku.models.product_register_detail.ProductDetailInfoParent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
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

    BroadcastReceiver connectivityReceiver = null;

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

    int positioncopy;


    String content;

    List<ModelSpinner> modelSpinners = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_register_detail);
        ButterKnife.bind(this);
        context = this;
        presenter.attachView(context, this);











        //check network broadcast reciever
        GeneralTools tools = GeneralTools.getInstance();
        connectivityReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tools.doCheckNetwork(ProductRegisterDetailActivity.this, findViewById(R.id.rl_root));
            }

        };

        presenter.viewLoaded();


        db = Room.databaseBuilder(context, AppDatabase.class, "detail")
                .allowMainThreadQueries()
                .build();
        db.detailDAO().deleteAll();





        btnRegisterDetail.setOnClickListener(v -> {

            List<MyModelSaveDB> listdata = new ArrayList<>();

            listdata = db.detailDAO().getAllMyModelSaveDB();

            List<SendDataId> senddata = new ArrayList<>();
            for (MyModelSaveDB m : listdata) {
                senddata.add(new SendDataId(m.getValue()));
            }

//            // to get RecyclerView EditTexts
//            EditText edt = findViewById(R.id.row_edtTitleTypeTextParent);

            String[] edtStrings = adapter.edtStrings;
            List<String> stringList = new ArrayList<String>(Arrays.asList(edtStrings));


            List<EditTextContents> edtList = new ArrayList<>();
            //getEditTextofRecyclereView
            for (int j = 0; j < App.productRegisterDetailDataList.data.size(); j++) {
//                listSpnDetail.add(App.productRegisterDetailDataList.data.get(position).option.get(j).title);
                if (App.productRegisterDetailDataList.data.get(j).type.equals("text")) {


                    String id = App.productRegisterDetailDataList.data.get(j).id;
                    String title = App.productRegisterDetailDataList.data.get(j).title;

                    if (stringList.get(j)== null) {
                        content = "ندارد";
                    }else{
                        content =stringList.get(j);
                    }

                    edtList.add(new EditTextContents(id, title, content));
                }

            }

            if (edtList != null) {
                presenter.sendList(edtList , modelSpinners);

            }

        });


    }

    @Override
    public void setRecyclerview() {
        recyclerViewProductRedisterDetailed.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new ParentAdapter(App.productRegisterDetailDataList, getApplicationContext(), (Presenter) presenter);
        recyclerViewProductRedisterDetailed.setAdapter(adapter);
    }









    @Override
    public void setSpinner(Spinner spinnerRowParent, int position, String SpnRowParentTitleId, String SpnRowParentTitle) {
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


               // text and id child spinner
                String txtChildContent = spinnerRowParent.getSelectedItem().toString();
                int position2 = spinnerRowParent.getSelectedItemPosition();

                // id and title parent spinner
              String titleId =   App.productRegisterDetailDataList.data.get(position).id;
              String titleContent =   App.productRegisterDetailDataList.data.get(position).title;



                if(!txtChildContent.equals("انتخاب کنید"))
                modelSpinners.add(new ModelSpinner(position,titleId,txtChildContent));

//               if(modelSpinners==null ||modelSpinners.size()==0){
//                   modelSpinners.add(new ModelSpinner(position,titleId,txtChildContent));
//               }else{
//
////                   modelSpinners.add(new ModelSpinner(position,titleId,txtChildContent));
//
//                       for (ModelSpinner a: modelSpinners) {
//
//                           if(a.getSpnId().equals(titleId)){
////                               modelSpinners.clear();
//                               modelSpinners.remove(modelSpinners.get(0));
//                               modelSpinners.add(new ModelSpinner(position,titleId,txtChildContent));
//                               Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
//
//                           }else{
////                               modelSpinners.clear();
//                               modelSpinners.remove(modelSpinners.get(0));
//                               modelSpinners.add(new ModelSpinner(position,titleId,txtChildContent));
//                               Toast.makeText(context, "not", Toast.LENGTH_SHORT).show();
//                           }
//
//                       }
//               }
//                int a = 5;







//
//                //////////////////////////////////meisam////////////////////////////////////////////
//                db = Room.databaseBuilder(context, AppDatabase.class, "detail")
//                        .allowMainThreadQueries()
//                        .build();
//
//                // to get a list of id of selected spnItems
//                int position2 = spinnerRowParent.getSelectedItemPosition();
//
//                if (firstCheck <= position) {
////                    myModelSavesArray.add(new SendDataId(App.productRegisterDetailDataList.data.get(position).option.get(position2).id, position));
//
//                    MyModelSaveDB myModelSaveDB = new MyModelSaveDB(position, App.productRegisterDetailDataList.data.get(position).option.get(position2).id);
//
//                    db.detailDAO().insertAll(myModelSaveDB);
//
//                }
//
//
//                if (firstCheck > position) {
//                    for (MyModelSaveDB model : db.detailDAO().getAllMyModelSaveDB())
//                        if (model.getSpnId() == position) {
//                            db.detailDAO().deleteItem(model);
//                            MyModelSaveDB myModelSaveDB = new MyModelSaveDB(position, App.productRegisterDetailDataList.data.get(position).option.get(position2).id);
//                            db.detailDAO().insertAll(myModelSaveDB);
//                        }
//                }
//
//                firstCheck++;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void setDetailInfo(ProductDetailInfoParent response) {
        txtShop.setText(response.data.getShop());
        txtCategoryProduct.setText(response.data.getCategory());
        txtCategory.setText(response.data.getSubCategory());
        txtSubCategory.setText(response.data.getSubCategory2());
        txtBrand.setText(response.data.getBrand());
        txtSubBrand.setText(response.data.getSubBrand());
        txtOwner.setText(response.data.getOwner());


        PhotoActivity.modelInfo = response;

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
