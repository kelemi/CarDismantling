package com.aofan.cardismantling.mvp.personalsetting;

import com.aofan.cardismantling.bean.UserInfo;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/28.
 */

public class PersonalSettingContract {


    public interface View extends BaseView<Presenter>
    {
        void getPersonalInfo();

        void onGetPersonalInfoSuccess(UserInfo userInfo);

        void onGetPersonalInfoError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getPersonalInfo(String userId);
    }
}
