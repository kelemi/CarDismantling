package com.aofan.cardismantling.mvp.personalsetting;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.LingJianKuCunListItem;
import com.aofan.cardismantling.bean.UserInfo;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
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

public class PersonalSettingPresenter implements PersonalSettingContract.Presenter {

    @NonNull
    PersonalSettingContract.View mPersonalSettingView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public PersonalSettingPresenter(@NonNull PersonalSettingContract.View mPersonalSettingView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mPersonalSettingView = mPersonalSettingView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mPersonalSettingView.setPresenter(this);
    }

    @Override
    public void getPersonalInfo(String userId) {
        mPersonalSettingView.showLoading();

        Subscription subscription = mHttpService.getUserInfoById(userId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("userInfoResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mPersonalSettingView.dismissLoading();
                            mPersonalSettingView.onGetPersonalInfoError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mPersonalSettingView.dismissLoading();
                        mPersonalSettingView.onGetPersonalInfoError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        JsonArray dataJsonArray = jsonObject.get("data").getAsJsonArray();

                        UserInfo userInfo = new Gson().fromJson(dataJsonArray.get(0),new TypeToken<UserInfo>(){}.getType());

                        mPersonalSettingView.dismissLoading();
                        mPersonalSettingView.onGetPersonalInfoSuccess(userInfo);
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
