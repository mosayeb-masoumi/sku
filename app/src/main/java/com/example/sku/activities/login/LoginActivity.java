package com.example.sku.activities.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sku.R;
import com.example.sku.activities.main.MainActivity;
import com.example.sku.helpers.PersianAppcompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends PersianAppcompatActivity implements Contract.View {
    Contract.Presenter presenter = new Presenter();
    Context context;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.pbLogin)
    ProgressBar pbLogin;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.rlButtons)
    RelativeLayout rlButtons;
    @BindView(R.id.llLogin)
    RelativeLayout llLogin;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        context = this;
        presenter.attachView(context, this);


        //todo test
        etEmail.setText("user2@sku.com");
        etPassword.setText("123456789");


        btLogin.setOnClickListener(v -> {


            email = etEmail.getText().toString().trim();
            password = etPassword.getText().toString().trim();
            presenter.btLoginClicked(email, password);
        });

    }


    @Override
    public void showEmpetyPassword() {
        etPassword.setError("لطفا کلمه ی عبور را وارد نمایید");
    }
    @Override
    public void showErrorEmpetyEmail() {
        etEmail.setError("لطفا ایمیل خود را وارد نمایید");
    }

    @Override
    public void hideBtnLogin() {
        btLogin.setVisibility(View.GONE);
        pbLogin.setVisibility(View.VISIBLE);
    }




}
