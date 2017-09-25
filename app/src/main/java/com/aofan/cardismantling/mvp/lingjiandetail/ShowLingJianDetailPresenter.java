package com.aofan.cardismantling.mvp.lingjiandetail;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.LingJianInfo;
import com.aofan.cardismantling.bean.LoginUser;
import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.ShareKey;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.CommonUtil;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ShowLingJianDetailPresenter implements ShowLingJianDetailContract.Presenter {

    @NonNull
    ShowLingJianDetailContract.View mShowLingJianDetailView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public ShowLingJianDetailPresenter(@NonNull ShowLingJianDetailContract.View mShowLingJianDetailView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mShowLingJianDetailView = mShowLingJianDetailView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mShowLingJianDetailView.setPresenter(this);
    }

    @Override
    public void getLingJianDetail(String id) {
        mShowLingJianDetailView.showLoading();

        Subscription subscription = mHttpService.getLingJianInfo(id)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("lingJianInfoResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mShowLingJianDetailView.dismissLoading();
                            mShowLingJianDetailView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mShowLingJianDetailView.dismissLoading();
                        mShowLingJianDetailView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        LingJianInfo lingJianInfo = (LingJianInfo) CommonUtil.formatJsonToObject(jsonObject,"data",new TypeToken<LingJianInfo>(){});

                        mShowLingJianDetailView.dismissLoading();
                        mShowLingJianDetailView.showLingJianDetail(lingJianInfo);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.unsubscribe();
        mSubscriptions = null;
    }
}
