package com.example.sku.activities.photo_activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.sku.R;
import com.example.sku.helpers.Converter;
import com.example.sku.helpers.GeneralTools;
import com.example.sku.helpers.PersianAppcompatActivity;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends PersianAppcompatActivity implements Contract.View, IPickResult {
    Contract.Presenter presenter = new Presenter();
    Context context;

    BroadcastReceiver connectivityReceiver = null;
    @BindView(R.id.rl_camera1)
    RelativeLayout rlCamera1;
    @BindView(R.id.rl_camera2)
    RelativeLayout rlCamera2;
    @BindView(R.id.rl_camera3)
    RelativeLayout rlCamera3;
    @BindView(R.id.rl_camera4)
    RelativeLayout rlCamera4;

    int status;
    Bitmap bm1, bm2, bm3, bm4;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.img3)
    ImageView img3;

    String strBm1 = "", strBm2="", strBm3="", strBm4="";
    @BindView(R.id.btSndpics)
    Button btSndpics;
    @BindView(R.id.pbSndpics)
    ProgressBar pbSndpics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        context = this;
        presenter.attachView(context, this);

        //check network broadcast reciever
        GeneralTools tools = GeneralTools.getInstance();
        connectivityReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tools.doCheckNetwork(PhotoActivity.this, findViewById(R.id.rl_root));
            }

        };


        rlCamera1.setOnClickListener(v -> {
            status = 1;
            choose_pic();
        });
        rlCamera2.setOnClickListener(v -> {
            status = 2;
            choose_pic();
        });
        rlCamera3.setOnClickListener(v -> {
            status = 3;
            choose_pic();
        });
        rlCamera4.setOnClickListener(v -> {
            status = 4;
            choose_pic();
        });


        btSndpics.setOnClickListener(v -> presenter.btSndPicsPressed(strBm1,strBm2,strBm3,strBm4));

    }

    private void choose_pic() {
        PickSetup setup = new PickSetup()
                .setTitle("settitle")
                .setProgressText("progress text")
                .setPickTypes(EPickType.CAMERA)
                .setFlip(true)
                .setSystemDialog(true);
        PickImageDialog.build(setup).show(this);
    }


    @Override
    public void onPickResult(PickResult r) {

        if (r.getError() == null) {
            if (status == 1) {

                img1.setImageBitmap(r.getBitmap());
                bm1 = r.getBitmap();

                strBm1 = Converter.bitmapToString(bm1);

//                requestImageUpdate(bm1);
                //jpg image
            }
            if (status == 2) {
                img2.setImageBitmap(r.getBitmap());
                bm2 = r.getBitmap();

                strBm2 = Converter.bitmapToString(bm2);
//                requestImageUpdate(bm2);

            }
            if (status == 3) {
                img3.setImageBitmap(r.getBitmap());
                bm3 = r.getBitmap();

                strBm3 = Converter.bitmapToString(bm3);
//                requestImageUpdate(bm3);

            }
            if (status == 4) {
                img4.setImageBitmap(r.getBitmap());
                bm4 = r.getBitmap();

                strBm4 = Converter.bitmapToString(bm4);

//                requestImageUpdate(bm4);

            }
        }
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

    @Override
    public void hidebtn() {
        btSndpics.setVisibility(View.GONE);
        pbSndpics.setVisibility(View.VISIBLE);
    }

    @Override
    public void showbtn() {
        btSndpics.setVisibility(View.VISIBLE);
        pbSndpics.setVisibility(View.GONE);
    }


}


