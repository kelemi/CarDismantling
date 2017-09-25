package com.aofan.cardismantling.mvp.lingjiandetail;

import com.aofan.cardismantling.bean.LingJianInfo;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ShowLingJianDetailContract {

    public interface View extends BaseView<Presenter>
    {

        void getLingJianDetail();

        void showLingJianDetail(LingJianInfo lingJianInfo);

        void showError(String tip);


        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getLingJianDetail(String id);
    }
}
