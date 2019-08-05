package com.example.sku.activities.product_mainlist_registerproduct;

import android.content.Context;

public interface Contract {
    interface View{

        void hideLoading();

        void setSpnOwner();

        void setSpnBrand();

        void setSpnCategory();

        void showLoading();


        void setSpnSubBrand();

        void setSpnSubCategory();

        void hideBtn();

        void setErrorEdtCategory(String strError);

        void setErrorEdtSubCategory(String strError);

        void setErrorEdtOwner(String strError);

        void setErrorEdtBrand(String strError);

        void setErrorEdtSubBrand(String strError);

        void showBtn();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void viewLoaded();

        void responseResult(int result);

        void requestDataSpnSubBrand(int itemPositionBrand);

        void requestDataSpnSubCategory(int itemPositionSpnCategory);

        void responseResultSpnSubBrand(int result);

        void responseResultSpnSubCategoryList2(int result);

        void btnRegisterPressed(String edtCategory, String edtSubCategoty, String edtOwner, String edtBrand, String edtSubBrand, 
                                int spnCategoryPosition, int spnSubCategoryPosition, int spnOwnerPosition, int spnBrandPosition, int spnSubBrandPosition);

        void productRegisterSendResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

        void requestSpnLists();

        void requestSpnSubBrand(String spnBrandId);

        void requestSpnSubCategory(String spnCategoryId);

        void requestRegisterProduct(String edtCategory, String edtSubCategoty, String edtOwner, String edtBrand, String edtSubBrand, int spnCategoryPosition, int spnSubCategoryPosition, int spnOwnerPosition, int spnBrandPosition, int spnSubBrandPosition);
    }
}
