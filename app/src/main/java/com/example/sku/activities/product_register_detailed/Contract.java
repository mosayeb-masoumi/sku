package com.example.sku.activities.product_register_detailed;

import android.content.Context;
import android.widget.Spinner;

import com.example.sku.models.product_register_detail.ProductDetailInfoParent;

import java.util.List;

public interface Contract {
    interface View{

        void setRecyclerview();
//
//        void setDetailInfo();

        void setSpinner(Spinner spinnerRowParent, int position ,String SpnRowParentTitleId,String SpnRowParentTitle);

        void setDetailInfo(ProductDetailInfoParent response);
    }

    interface Presenter {

        void attachView (Context context,View view);

        void viewLoaded();

        void productRegisterDetailDataListResult(int result, String productId);

        void productDetailInfoResult(int result);


         void setSpinner(Spinner spinnerRowParent, int position ,String SpnRowParentTitleId,String SpnRowParentTitle);

        void sendList(List<EditTextContents> editTextContents, List<ModelSpinner> modelSpinners);

        void setDetailInfoParent(ProductDetailInfoParent response);
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

        void viewLoade();

        void requestRegisterDetailInfo(String productId, boolean ok204);

        void requestSendList(List<EditTextContents> senddata, List<ModelSpinner> modelSpinners);
    }
}
