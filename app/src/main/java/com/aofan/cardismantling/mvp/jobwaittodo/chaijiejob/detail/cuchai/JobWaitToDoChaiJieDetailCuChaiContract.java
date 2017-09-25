package com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob.detail.cuchai;

import com.aofan.cardismantling.bean.ChaiJieDetailInfoForCuChai;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public interface JobWaitToDoChaiJieDetailCuChaiContract {

    public interface View extends BaseView<Presenter>
    {

        void showLoading();

        void dismissLoading();

        void getChaiJiePaiGongDanDetail();

        void showChaiJiePaiGongDanDetail(ChaiJieDetailInfoForCuChai chaiJieDetail);

        void showError(String tip);

        void submitLingJianChaiJieInfo(List<File> picFileList);

        void onSubmitLingJianChaiJieInfoSuccess();

        void onSubmitLingJianChaiJieInfoError(String tip);

        void finishChaiJie();

        void onFinishChaiJieSuccess();

        void onFinishChaiJieError(String tip);

    }

    public interface Presenter extends BasePresenter
    {
        /**
         * 获取拆解派工单详情
         * @param oid  派工单id
         */
        void getChaiJiePaiGongDanDetail(String oid,String chaiJieType);

        void submitLingJianChaiJieInfo(String chaiJieType,String userId, String oId, List<File> lingJianChaiJiePics);

        void finishChaiJie(String userId,String oId);
    }

}
