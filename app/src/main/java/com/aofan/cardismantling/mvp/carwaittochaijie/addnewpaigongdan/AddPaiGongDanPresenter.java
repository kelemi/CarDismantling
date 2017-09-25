package com.aofan.cardismantling.mvp.carwaittochaijie.addnewpaigongdan;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.google.gson.JsonObject;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/24.
 */

public class AddPaiGongDanPresenter implements AddPaiGongDanContract.Presenter {

    @NonNull
    AddPaiGongDanContract.View mAddPaiGongDanView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public AddPaiGongDanPresenter(@NonNull AddPaiGongDanContract.View mAddPaiGongDanView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mAddPaiGongDanView = mAddPaiGongDanView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mAddPaiGongDanView.setPresenter(this);
    }

    @Override
    public void addPaiGongDan(String chaijieType,String userId, String analysisCarId, String chaiJieRequire, String chaiJieWorkerNames, String chaiJieWorkIdAndNames, String chaiJieLingJianNames, String chaiJieLingJianIdAndNames) {
        mAddPaiGongDanView.showLoading();

        Subscription subscription = mHttpService.addNewPaiGongDan(chaijieType,userId,analysisCarId,chaiJieRequire,chaiJieWorkerNames,chaiJieWorkIdAndNames,chaiJieLingJianNames,chaiJieLingJianIdAndNames)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("oneCarPaiGongDanListResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mAddPaiGongDanView.dismissLoading();
                            mAddPaiGongDanView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mAddPaiGongDanView.dismissLoading();
                        mAddPaiGongDanView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        mAddPaiGongDanView.dismissLoading();
                        mAddPaiGongDanView.showAddPaiGongDanSuccess();


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
