package com.aofan.cardismantling.mvp.carwaittochaijie.paigongdan.hasfinishedpaigongcar;

import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * 未完成派工的汽车列表
 * Created by Administrator on 2016/11/24.
 */

public class HasFinishedPaiGongCarListContract {

    public interface View extends BaseView<Presenter>
    {
        void getHasFinishedPaiGongCarList();

        void showHasFinishedPaiGongCarList();

        void showError(String tip);
    }

    public interface Presenter extends BasePresenter
    {
        void getHasFinishedPaiGongCarList();
    }
}
