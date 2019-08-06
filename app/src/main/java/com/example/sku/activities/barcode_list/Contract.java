package com.example.sku.activities.barcode_list;

import android.content.Context;

public interface Contract {
    interface View{

        void hideBtn();

        void setRecyclerview();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void btnProductRegisterPressed();

        void btnBarcodeRegisterPressed();

        void viewLoaded();

        void barcodeProductsList(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);
        

        void requestBarcodeProductList();
    }
}