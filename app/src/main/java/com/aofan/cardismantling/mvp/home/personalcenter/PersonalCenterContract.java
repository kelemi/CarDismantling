package com.aofan.cardismantling.mvp.home.personalcenter;

import com.aofan.cardismantling.bean.VersionInfo;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/28.
 */

public class PersonalCenterContract {

    public interface View extends BaseView<Presenter>
    {
        void getVersionInfo();

        void onGetVersionInfoSuccess( VersionInfo versionInfo);

        void onGetVersionInfoError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getVersionInfo();
    }

}
