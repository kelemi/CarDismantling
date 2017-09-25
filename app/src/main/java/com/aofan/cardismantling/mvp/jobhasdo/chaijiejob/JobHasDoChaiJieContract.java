package com.aofan.cardismantling.mvp.jobhasdo.chaijiejob;

import com.aofan.cardismantling.bean.HasDoChaiJieTaskItem;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class JobHasDoChaiJieContract {

    public interface View extends BaseView<Presenter>
    {
        void getHasDoChaiJieJob();

        void showHasDoChaiJieJob(List<HasDoChaiJieTaskItem> mChaiJieTaskList);

        void showError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getHasDoChaiJieJob(String userId,String jobKind,String pageIndex,String pageSize);
    }
}
