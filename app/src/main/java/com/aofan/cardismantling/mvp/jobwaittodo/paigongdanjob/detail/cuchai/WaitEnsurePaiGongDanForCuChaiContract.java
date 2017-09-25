package com.aofan.cardismantling.mvp.jobwaittodo.paigongdanjob.detail.cuchai;

import com.aofan.cardismantling.bean.ChaiJieDetailInfoForCuChai;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/28.
 */

public class WaitEnsurePaiGongDanForCuChaiContract {

    public interface View extends BaseView<Presenter>
    {



        void ensurePaiGongDanFinished();

        void showEnsurePaiGongDanFinishedSuccess();

        void showEnsurePaiGongDanFinishedError(String tip);

        void getChaiJiePaiGongDanDetail();

        void showChaiJiePaiGongDanDetail(ChaiJieDetailInfoForCuChai chaiJieDetail);

        void showChaiJiePaiGongDanDetailError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        /**
         * 获取拆解派工单详情
         * @param oid  派工单id
         */
        void getChaiJiePaiGongDanDetail(String chaijieType , String oid);


        void ensurePaiGongDanFinished(String oId,String userId);
    }

}
