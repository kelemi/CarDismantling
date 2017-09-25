package com.aofan.cardismantling.mvp.carwaitanalysis.carwaitanalysisdetail;

import com.aofan.cardismantling.bean.CarLingJianInfo;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */

public class WaitToAnalysisCarDetailContract {

    public interface View extends BaseView<Presenter>
    {

        void getFeiQiWuInfo();

        void showGetFeiQiWuInfoSuccess(List<CarLingJianInfo> dianChiFeiQiWuParts,List<CarLingJianInfo> otherFeiQiWuParts,List<CarLingJianInfo> queShiDianChiFeiQiWuParts,List<CarLingJianInfo> queShiOtherFeiQiWuParts);

        void showGetFeiQiWuInfoError(String tip);


        void getWaitToAnalysisCarLingJianInfo();

        void showWaitToAnalysisCarLingJianInfo(List<CarLingJianInfo> faDongJiLingJianList,List<CarLingJianInfo> diPanLingJianInfo,List<CarLingJianInfo> jiaShiShiLingJianInfo,List<CarLingJianInfo> cheXiangLingJianInfo);

        void showGetWaitToAnalysisCarLingJianInfoError(String tip);


        void submitCarAnalysisInfo();

        void showSubmitCarAnalysisInfoSuccess();

        void showSubmitCarAnalysisInfoError(String tip);

        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter
    {

        void getFeiQiWuInfo(String userId);

        void getWaitToAnalysisCarLingJianInfo(String userId);

        void submitCarAnalysisInfoCuChai(String carId,String chaiJieWay,String userId,String feiQiWuChaiJieInfo);

        /**
         * 提交零件分析信息
         * @param carId
         * @param chaiJieWay
         * @param userId
         * @param analysisLingJianInfo   分析的零件
         * @param unAnalysisLingJianInfo 未分析选择的零件信息
         */
        void submitCarAnalysisInfo(String carId,String chaiJieWay,String userId,String analysisLingJianInfo,String unAnalysisLingJianInfo);

    }
}
