package com.aofan.cardismantling.mvp.jobhasdo.paigongdanjob.detail.cuchai;

import com.aofan.cardismantling.bean.ChaiJieDetailInfoForCuChai;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/29.
 */

public class JobHasEnsurePaiGongDanDetailForCuChaiContract {

    public interface View extends BaseView<Presenter>
    {
        void getJobHasDoPaiGongDanDetail();

        void onGetJobHasDoPaiGongDanDetailSuccess(ChaiJieDetailInfoForCuChai chaiJieDetail);

        void onGetJobHasDoPaiGongDanDetailError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getJobHasDoPaiGongDanDetail(String chaijieType,String oid);
    }
}
