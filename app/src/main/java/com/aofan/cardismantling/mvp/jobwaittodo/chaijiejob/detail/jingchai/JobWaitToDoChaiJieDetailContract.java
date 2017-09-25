package com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob.detail.jingchai;

import com.aofan.cardismantling.bean.ChaiJieDetailInfo;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */

public class JobWaitToDoChaiJieDetailContract {

    public interface View extends BaseView<Presenter>
    {

        void showLoading();

        void dismissLoading();

        void getChaiJiePaiGongDanDetail();

        void showChaiJiePaiGongDanDetail(ChaiJieDetailInfo chaiJieDetail);

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
        void getChaiJiePaiGongDanDetail(String oid);

        void submitLingJianChaiJieInfo(String chaiJieType,String userId, String oId, String partId, String partName , String lingJianCount, List<File> lingJianChaiJiePics);

        void finishChaiJie(String userId,String oId);
    }
}
