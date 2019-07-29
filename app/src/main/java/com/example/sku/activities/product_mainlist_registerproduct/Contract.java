package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.Context;

public interface Contract {
    interface View{

    }

    interface Presenter {

        void attachView (Context context,View view);

        void viewLoaded();
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

    }
}
