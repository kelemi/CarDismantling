package com.aofan.cardismantling.mvp.carwaittochaijie.choosechaijielingjian;

import com.aofan.cardismantling.bean.LingJIanOfCanChooseChaiJie;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */

public class ChooseChaiJieLingJianContract {

    public interface View extends BaseView<Presenter>
    {
        void getChaiJieLingJian();

        void showChaiJieLingJian(List<LingJIanOfCanChooseChaiJie> allCanChooseChaiJieLingJianList);

        void showGetChaiJieLingJianError(String tip);

        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter
    {
        void getChaiJieLingJian(String hasAnalysisCarId
        ,String analysisState);
    }

}
