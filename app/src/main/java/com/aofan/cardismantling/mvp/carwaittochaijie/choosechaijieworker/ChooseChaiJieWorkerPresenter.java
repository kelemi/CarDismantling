package com.aofan.cardismantling.mvp.carwaittochaijie.choosechaijieworker;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.ChaiJieWorkerInfo;
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
 * Created by Administrator on 2016/11/24.
 */

public class ChooseChaiJieWorkerPresenter implements ChooseChaiJieWorkerContract.Presenter {


    @NonNull
    ChooseChaiJieWorkerContract.View mChooseChaiJieWorkerView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public ChooseChaiJieWorkerPresenter(@NonNull ChooseChaiJieWorkerContract.View mChooseChaiJieWorkerView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mChooseChaiJieWorkerView = mChooseChaiJieWorkerView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mChooseChaiJieWorkerView.setPresenter(this);
    }

    @Override
    public void getChaiJieWorkers(String userId) {
        mChooseChaiJieWorkerView.showLoading();

        Subscription subscription = mHttpService.getCanChooseChaiJieWorkers(userId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("canChooseChaiJieWorker:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mChooseChaiJieWorkerView.dismissLoading();
                            mChooseChaiJieWorkerView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mChooseChaiJieWorkerView.dismissLoading();
                        mChooseChaiJieWorkerView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<ChaiJieWorkerInfo> chaiJieWorkerInfoList = new ArrayList<ChaiJieWorkerInfo>();

                        if (jsonObject.get("data") == null)
                        {
                            mChooseChaiJieWorkerView.dismissLoading();
                            mChooseChaiJieWorkerView.showChaiJieWorkers(chaiJieWorkerInfoList);
                        }else {

                            chaiJieWorkerInfoList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken< List<ChaiJieWorkerInfo>>(){});
                            mChooseChaiJieWorkerView.dismissLoading();
                            mChooseChaiJieWorkerView.showChaiJieWorkers(chaiJieWorkerInfoList);

                        }


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
