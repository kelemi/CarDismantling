package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.detail;

import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.bean.WeiChuChaijieDetail;

/**
 * Created by Administrator on 2016/11/23.
 */

public class WeiChuDetailContract {

    public interface View extends BaseView<Presenter> {

        void getChaiJieDetailFail(String tip);

        void getChaiJieDetailSuccess(WeiChuChaijieDetail weiChuChaijieDetail);

        void saveCompleterSuccess(String tip);

        void saveCompleterFail(String tip);

        void netError(String tip);

        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter {

        //分析id
        void getChaiJieDetail(String did);

        //完成拆解接口
        void no_saveCompleteOrder(String did);
    }
}
