package com.aofan.cardismantling.mvp.jobwaittodo.chaijiejob;

import com.aofan.cardismantling.bean.JobWaitToDoChaiJieTaskItem;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class JobWaitToDoChaiJieContract {

    public interface View extends BaseView<Presenter>
    {
        void getWaitToDoChaiJieJob();

        void showWaitToDoChaiJieJob(List<JobWaitToDoChaiJieTaskItem> mChaiJieTaskList);

        void showError(String tip);

        void showLoading();

        void dismissLoading();
    }

    public interface Presenter extends BasePresenter
    {
        void getWaitToDoChaiJieJob(String userId,String jobKind,String pageIndex,String pageSize);

    }
}
