package com.aofan.cardismantling.mvp.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aofan.cardismantling.R;
import com.aofan.cardismantling.app.CustomApplication;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.mvp.base.BaseActivity;
import com.aofan.cardismantling.mvp.home.ActivityHome;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.ToastUtil;
import com.aofan.cardismantling.util.schedulers.SchedulerProvider;
import com.jakewharton.rxbinding.view.RxView;

import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/10/26.
 */
public class ActivityLogin extends BaseActivity implements LoginContract.View {


    private EditText etAccount;
    private EditText etPassword;
    private TextView tvLogin;


    LoginContract.Presenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((Boolean) CommonUtil.getShareValue(ShareKey.SHARE_KEY_IS_LOGIN, false)) {
            startActivity(new Intent(this, ActivityHome.class));
            finish();
        } else {
            setContentView(R.layout.activity_login);
            mLoginPresenter = new LoginPresenter(this, SchedulerProvider.getInstance(), CustomApplication.getInstance().getHttpService());
            initData();
            assignView();
            initView();
        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void assignView() {
        etAccount = (EditText) findViewById(R.id.et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvLogin = (TextView) findViewById(R.id.tv_login);
    }

    @Override
    public void initView() {
        RxView.clicks(tvLogin)
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        boolean result = false;
                        if (!CommonUtil.isEditTextEmpty(etAccount, "请输入账号信息")
                                && !CommonUtil.isEditTextEmpty(etPassword, "请输入密码")) {
                            result = true;
                        }

                        return result;
                    }
                }).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                login();
            }
        });
    }

    @Override
    public void login() {
        mLoginPresenter.login(etAccount.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void showLoginResult() {
        ToastUtil.showShort(this, "登录成功");
        startActivity(new Intent(ActivityLogin.this, ActivityHome.class));
        finish();
    }

    @Override
    public void showError(String tip) {
        ToastUtil.showShort(this, tip);
    }


    @Override
    public void showLoading() {
        showProgressDialog(false, null);
    }

    @Override
    public void dismissLoading() {
        dismissProgressDialog();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
