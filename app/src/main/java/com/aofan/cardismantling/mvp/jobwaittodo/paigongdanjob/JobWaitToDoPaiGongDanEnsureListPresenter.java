package com.aofan.cardismantling.mvp.jobwaittodo.paigongdanjob;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.PaiGongDanOfEnsure;
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
 * Created by Administrator on 2016/11/23.
 */

public class JobWaitToDoPaiGongDanEnsureListPresenter implements JobWaitToDoPaiGongDanEnsureListContract.Presenter {


    @NonNull
    JobWaitToDoPaiGongDanEnsureListContract.View jobToDoPaiGongDanEnsureView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public JobWaitToDoPaiGongDanEnsureListPresenter(@NonNull JobWaitToDoPaiGongDanEnsureListContract.View jobToDoPaiGongDanEnsureView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.jobToDoPaiGongDanEnsureView = jobToDoPaiGongDanEnsureView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.jobToDoPaiGongDanEnsureView.setPresenter(this);
    }

    @Override
    public void getWaitToEnsurePaiGongDanInfoList(String userId,String jobKind,String pageIndex,String pageSize) {
        jobToDoPaiGongDanEnsureView.showLoading();

        Subscription subscription = mHttpService.getUserWaitToDoJobList(userId,jobKind,pageIndex,pageSize)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("userWaitToDoPaiGongDanResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            jobToDoPaiGongDanEnsureView.dismissLoading();
                            jobToDoPaiGongDanEnsureView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        jobToDoPaiGongDanEnsureView.dismissLoading();
                        jobToDoPaiGongDanEnsureView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                       List<PaiGongDanOfEnsure> paiGongDanList = new ArrayList<>();

                        if (jsonObject.get("data").isJsonNull()==false)
                        {
                            paiGongDanList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<PaiGongDanOfEnsure>>(){});
                        }
                        jobToDoPaiGongDanEnsureView.dismissLoading();
                        jobToDoPaiGongDanEnsureView.showWaitToEnsurePaiGongDanList(paiGongDanList);

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
