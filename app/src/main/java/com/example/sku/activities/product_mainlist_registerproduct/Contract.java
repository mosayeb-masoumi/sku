package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.Context;

public interface Contract {
    interface View{

        void hideLoading();

        void setSpnOwner();

        void setSpnBrand();

        void setSpnCategory();

        void showLoading();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void viewLoaded();

        void responseResult(int result);

        void requestDataSpnSubBrand(int itemPositionBrand);
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

        void requestSpnLists();

        void requestSpnSubBrand(int itemPositionBrand);
    }
}
