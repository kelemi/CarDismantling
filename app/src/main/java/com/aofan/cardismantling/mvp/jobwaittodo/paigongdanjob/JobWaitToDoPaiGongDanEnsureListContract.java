package com.aofan.cardismantling.mvp.jobwaittodo.paigongdanjob;

import com.aofan.cardismantling.bean.ChaiJieDetailInfo;
import com.aofan.cardismantling.bean.PaiGongDanOfEnsure;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class JobWaitToDoPaiGongDanEnsureListContract {

    public interface View extends BaseView<Presenter>
    {
        void getWaitToEnsurePaiGongDanInfoList();

        void showWaitToEnsurePaiGongDanList(List<PaiGongDanOfEnsure> paiGongDanList);

        void showError(String tip);
        ;

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {


        void getWaitToEnsurePaiGongDanInfoList(String userId,String jobKind,String pageIndex,String pageSize);
    }

}
