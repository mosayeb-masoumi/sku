package com.example.sku.activities.main;

import android.content.Context;

import java.util.List;

public interface Contract {
    interface View{


        void setFamilySpinner();

        void hideBtnFamily();

        void showBtnFamily();

        void setShopSpinner();

        void hideBtnChooseShop();

        void showBtnChooseshop();
    }

    interface Presenter {

        void attachView (Context context, View view);

        void loadView();

        void sopListResult(int result);

        void btnNewShopPressed();

        void requestCategoryList();

        void getCategoryListResult(int result);

        void btnRegiserCodePressed();

        void requestShopList();
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requestShopList();

        void requestCategoryList();

        void requestPermissionCamera();
    }
}
