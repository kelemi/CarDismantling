package com.aofan.cardismantling.mvp.datatongji.workertongji;

import com.aofan.cardismantling.bean.TongJiDataOfWorker;
import com.aofan.cardismantling.mvp.base.BasePresenter;
import com.aofan.cardismantling.mvp.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class WorkerTongJiContract {

    public interface View extends BaseView<Presenter>
    {
        void getWorkerTongJiData();

        void onGetWorkerTongJiDataSuccess(List<TongJiDataOfWorker> workerTongJiData);

        void onGetWorkerTongJiDataError(String tip);

        void showLoading();

        void dismissLoading();

    }

    public interface Presenter extends BasePresenter
    {
        void getWorkerTongJiData(String userId,String pageIndex,String pageSize);
    }
}
