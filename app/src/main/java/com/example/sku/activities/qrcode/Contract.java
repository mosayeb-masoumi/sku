package com.example.sku.activities.qrcode;

import android.content.Context;

public interface Contract {
    interface View{

        void hideBtn();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void btnRegisterPressed(String barcodResult);
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

        void requestSendBarcode(String barcodResult);
    }
}
