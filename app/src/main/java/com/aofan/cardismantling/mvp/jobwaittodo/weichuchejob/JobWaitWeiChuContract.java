package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob;

import com.aofan.cardismantling.bean.JobWaitWeiChuItem;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class JobWaitWeiChuContract {

    public interface View extends BaseView<Presenter> {

        void getUnSignCarListFail(String tip);

        void getUnSignCarListSuccess(List<JobWaitWeiChuItem> mJobWaitWeiChus);

        void netError(String tip);

        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter {

        void getUnSignCarList(String userid, String rows, String page);

    }
}
