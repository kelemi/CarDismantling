package com.aofan.cardismantling.mvp.datatongji.workertongji;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.TongJiDataOfWorker;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/29.
 */

public class WorkerTongJiPresenter implements WorkerTongJiContract.Presenter {


    @NonNull
    WorkerTongJiContract.View mWorkerTongJiView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public WorkerTongJiPresenter(@NonNull WorkerTongJiContract.View mWorkerTongJiView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mWorkerTongJiView = mWorkerTongJiView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mWorkerTongJiView.setPresenter(this);
    }


    @Override
    public void getWorkerTongJiData(String userId,String pageIndex,String pageSize) {
        mWorkerTongJiView.showLoading();

        Subscription subscription = mHttpService.getWorkerTongJiData(userId,pageIndex,pageSize)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("workerTongJiData:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mWorkerTongJiView.dismissLoading();
                            mWorkerTongJiView.onGetWorkerTongJiDataError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
                        }

                        return jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS);
                    }
                })
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mWorkerTongJiView.dismissLoading();
                        mWorkerTongJiView.onGetWorkerTongJiDataError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<TongJiDataOfWorker> workerTongJiData = new ArrayList<TongJiDataOfWorker>();

                        if (jsonObject.get("data").isJsonNull()==false)
                        {
                            workerTongJiData = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<TongJiDataOfWorker>>(){});
                        }

                        mWorkerTongJiView.dismissLoading();
                        mWorkerTongJiView.onGetWorkerTongJiDataSuccess(workerTongJiData);

                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
