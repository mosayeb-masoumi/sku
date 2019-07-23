package com.example.sku.activities.splash;

import android.content.Context;

public interface Contract {
    interface View{

    }

    interface Presenter {

        void attachView (Context context,View view);

        void activityLoaded();
    }

    interface Model{

        void attachPresenter (Presenter presenter , Context context);

    }
}
