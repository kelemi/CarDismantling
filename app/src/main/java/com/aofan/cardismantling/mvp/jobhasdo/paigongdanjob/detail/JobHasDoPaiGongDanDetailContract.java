package com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob.detail;

import com.aofan.cardismantling.bean.ChaiJieDetailInfo;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/29.
 */

public class JobHasDoPaiGongDanDetailContract {

    public interface View extends BaseView<Presenter>
    {
        void getJobHasDoPaiGongDanDetail();

        void onGetJobHasDoPaiGongDanDetailSuccess(ChaiJieDetailInfo chaiJieDetail);

        void onGetJobHasDoPaiGongDanDetailError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getJobHasDoPaiGongDanDetail(String oid);
    }
}
