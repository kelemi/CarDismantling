package com.aofan.cardismantling.mvp.jobwaittodo;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.LoginUser;
import com.aofan.cardismantling.bean.UserToDoJobKind;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/28.
 */

public class JobWaitToDoMainViewPresenter implements JobWaitToDoMainViewContract.Presenter {


    @NonNull
    JobWaitToDoMainViewContract.View mJobWaitToDoMainView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public JobWaitToDoMainViewPresenter(@NonNull JobWaitToDoMainViewContract.View mJobWaitToDoMainView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mJobWaitToDoMainView = mJobWaitToDoMainView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mJobWaitToDoMainView.setPresenter(this);
    }

    @Override
    public void getUserWaitToDoJobKind(String userId) {
        mJobWaitToDoMainView.showLoading();

        Subscription subscription = mHttpService.getUserWaitToDoJobKind(userId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("userJobWaitToDoKindResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mJobWaitToDoMainView.dismissLoading();
                            mJobWaitToDoMainView.onGetWaitToDoJobKindError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mJobWaitToDoMainView.dismissLoading();
                        mJobWaitToDoMainView.onGetWaitToDoJobKindError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<UserToDoJobKind> userToDoJobKindList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<UserToDoJobKind>>(){});

                        mJobWaitToDoMainView.dismissLoading();
                        mJobWaitToDoMainView.onGetWaitToDoJobKindSuccess(userToDoJobKindList);
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
