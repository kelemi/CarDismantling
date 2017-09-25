package com.aofan.cardismantling.mvp.jobhasdo.chaijiejob.detail.jingchai;

import com.aofan.cardismantling.bean.ChaiJieDetailInfo;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/29.
 */

public class JobHasDoChaiJieDetailContract {

    public interface View extends BaseView<Presenter>
    {
         void getHasDoChaiJieDetail();

        void onGetHasDoChaiJieDetailSuccess(ChaiJieDetailInfo chaiJieDetailInfo);

        void onGetHasDoChaiJieDetailError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getHasDoChaiJieDetail(String oId);
    }
}
