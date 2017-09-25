package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.JobWaitWeiChuItem;
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

public class JonWaitWeiChuPresenter implements JobWaitWeiChuContract.Presenter {


    @NonNull
    JobWaitWeiChuContract.View mMainView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public JonWaitWeiChuPresenter(@NonNull JobWaitWeiChuContract.View mMainView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mMainView = mMainView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mMainView.setPresenter(this);
    }


    @Override
    public void getUnSignCarList(String userid, String rows, String page) {
        mMainView.showLoading();

        Subscription subscription = mHttpService.getUnSignCarList(userid, rows, page)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("getUnSignCarListResponse:" + jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS)) {
                            mMainView.dismissLoading();
                            mMainView.getUnSignCarListFail(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mMainView.dismissLoading();
                        mMainView.netError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        mMainView.dismissLoading();
                        List<JobWaitWeiChuItem> jobWaitWeiChuItems = new ArrayList<>();

                        if (jsonObject.get("data").isJsonNull() == false) {
                            jobWaitWeiChuItems = CommonUtil.formatJsonToList(jsonObject, "data", new TypeToken<List<JobWaitWeiChuItem>>() {
                            });
                        }
                        mMainView.getUnSignCarListSuccess(jobWaitWeiChuItems);

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
