package com.example.sku.activities.main;

import android.content.Context;

public interface Contract {
    interface View{

    }

    interface Presenter {

        void attachView (Context context, View view);

        void loadView();

        void sopListResult(int result);




        void btnNewShopPressed();
    }

    interface Model{

        void attachPresenter (Presenter presenter ,Context context);

        void requestShopList();
    }
}
