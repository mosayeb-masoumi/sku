package com.example.sku.activities.internet_check;


import android.content.Context;

public interface Contract {
    interface View{

    }

    interface Presenter {

        void attachView (Context context,View view);

        void btnConnectDataClicked();

        void btnConnectWifiClicked();
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

    }
}
