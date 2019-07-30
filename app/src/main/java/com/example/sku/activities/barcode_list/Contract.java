package com.example.sku.activities.barcode_list;

import android.content.Context;

public interface Contract {
    interface View{

        void hideBtn();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void btnProductRegisterPressed();

        void btnBarcodeRegisterPressed();
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

        void requestGetListOfSpinners();
    }
}
