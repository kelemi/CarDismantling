package com.aofan.cardismantling.mvp.modifypass;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.CarInfo;
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
 * Created by Administrator on 2016/11/28.
 */

public class ModifyPassPresenter implements ModifyPassContract.Presenter {


    @NonNull
    ModifyPassContract.View mModifyPassView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public ModifyPassPresenter(@NonNull ModifyPassContract.View mModifyPassView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mModifyPassView = mModifyPassView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mModifyPassView.setPresenter(this);
    }

    @Override
    public void modifyPass(String userId, String oldPass, String newPass) {
        mModifyPassView.showLoading();

        Subscription subscription = mHttpService.modifyPass(userId,oldPass,newPass)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("modifyPassResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mModifyPassView.dismissLoading();
                            mModifyPassView.showModifyPassError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mModifyPassView.dismissLoading();
                        mModifyPassView.showModifyPassError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {


                        mModifyPassView.dismissLoading();
                        mModifyPassView.showModifyPassSuccess();
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
