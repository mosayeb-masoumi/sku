package com.example.sku.activities.login;

import android.content.Context;

public interface Contract {
    interface View{

      

        void showEmpetyPassword();

        void hideBtnLogin();


        void showErrorEmpetyEmail();
    }

    interface Presenter {

        void attachView (Context context,View view);

        void btLoginClicked(String email, String password);


        void loginResult(int result);
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

        void requestLogin(String email, String password);
    }
}
