package com.aofan.cardismantling.mvp.chaijiehasfinisheddetail;

import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

/**
 * Created by Administrator on 2016/11/24.
 */

public class ChaiJieFinishedDetailContract {

    public interface View extends BaseView<Presenter>
    {
        void getChaiJieDetail();

        void showChaiJieDetail();

        void showError(String tip);
    }

    public interface Presenter extends BasePresenter
    {
        void getChaiJieDetail();
    }
}
