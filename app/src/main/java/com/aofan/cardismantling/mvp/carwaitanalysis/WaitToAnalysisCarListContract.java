package com.aofan.cardismantling.mvp.carwaitanalysis;

import com.aofan.cardismantling.bean.CarInfo;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**待分析车辆列表contract
 * Created by Administrator on 2016/11/24.
 */

public class WaitToAnalysisCarListContract {

    public interface View extends BaseView<Presenter>
    {
        void getWaitToAnalysisCar();

        void showWaitToAnalysisCar(List<CarInfo> carList);

        void showError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getWaitToAnalysisCar(String userId,String carAnalysisState,String pageIndex,String pageSize);


    }

}
