package com.example.sku.activities.barcode_list;

import android.content.Context;

import com.example.sku.models.barcode_list.BarcodeProductsList;

public interface Contract {
    interface View{

        void hideBtn();

        void setRecyclerview(BarcodeProductsList barcodeProductsList);

        void showLoading();

        void stopLoading();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void btnProductRegisterPressed();

        void btnBarcodeRegisterPressed();

        void viewLoaded();

        void barcodeProductsList(int result);

        void setRecyclereView(BarcodeProductsList barcodeProductsList);
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);
        

        void requestBarcodeProductList();
    }
}
