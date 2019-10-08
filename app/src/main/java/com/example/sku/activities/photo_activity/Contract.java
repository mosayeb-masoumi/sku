package com.example.sku.activities.photo_activity;

import android.content.Context;

public interface Contract {
    interface View{

        void hidebtn();

        void showbtn();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void btSndPicsPressed(String strBm1, String strBm2, String strBm3, String strBm4);


        void sendpisResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

        void requestSendPics(String strBm1, String strBm2, String strBm3, String strBm4);
    }
}
