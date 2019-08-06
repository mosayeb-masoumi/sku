package com.example.sku.activities.product_register_detailed;

import android.content.Context;
import android.widget.Spinner;

public interface Contract {
    interface View{

        void setRecyclerview();

        void setDetailInfo();

        void setSpinner(Spinner spinnerRowParent, int position);
    }

    interface Presenter {

        void attachView (Context context,View view);

        void viewLoaded();

        void productRegisterDetailDataListResult(int result, String productId);

        void productDetailInfoResult(int result);


         void setSpinner(Spinner spinnerRowParent, int position);
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

        void viewLoade();

        void requestRegisterDetailInfo(String productId);
    }
}
