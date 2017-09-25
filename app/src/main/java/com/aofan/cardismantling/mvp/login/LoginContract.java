package com.aofan.cardismantling.mvp.login;

import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/10/26.
 */
public class LoginContract  {



    public interface View extends BaseView<Presenter>
    {

        void login();

        void showLoginResult();

        void showError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {

        void login(String userName,String passWord);
    }

}
