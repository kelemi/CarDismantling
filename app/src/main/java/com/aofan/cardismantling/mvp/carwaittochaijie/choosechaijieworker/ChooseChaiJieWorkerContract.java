package com.aofan.cardismantling.mvp.carwaittochaijie.choosechaijieworker;

import com.aofan.cardismantling.bean.ChaiJieWorkerInfo;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */

public class ChooseChaiJieWorkerContract {

    public interface View extends BaseView<Presenter>
    {
        void getChaiJieWorkers();

        void showChaiJieWorkers(List<ChaiJieWorkerInfo> chaiJieWorkerInfoList);

        void showError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getChaiJieWorkers(String userId);
    }
}
