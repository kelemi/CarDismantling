package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.addcar;

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
 * Created by Administrator on 2016/11/23.
 */

public class AddWeiChuPresenter implements AddWeiChuContract.Presenter {


    @NonNull
    AddWeiChuContract.View mMainView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public AddWeiChuPresenter(@NonNull AddWeiChuContract.View mMainView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mMainView = mMainView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mMainView.setPresenter(this);
    }

    @Override
    public void addUnSignCar(String userid, String VehicleNumber, String CustomInFactoryNumber, String cjtype) {
        mMainView.showLoading();

        Subscription subscription = mHttpService.addUnSignCar(userid, VehicleNumber, CustomInFactoryNumber, cjtype)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("addResponse:" + jsonObject.toString());
                        //{"staues":"sucess","message":"提交成功"}
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS)) {
                            mMainView.dismissLoading();
                            mMainView.addUnSignCarFail(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        String message = jsonObject.get("message").getAsString();
                        mMainView.addUnSignCarSuccess(message);

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
