package com.aofan.cardismantling.mvp.carwaitanalysis;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.CarInfo;
import com.aofan.cardismantling.bean.MenuItem;
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

public class WaitToAnalysisCarListPresenter implements WaitToAnalysisCarListContract.Presenter {


    @NonNull
    WaitToAnalysisCarListContract.View mWaitToAnalysisCarListView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public WaitToAnalysisCarListPresenter(@NonNull WaitToAnalysisCarListContract.View mWaitToAnalysisCarListView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mWaitToAnalysisCarListView = mWaitToAnalysisCarListView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mWaitToAnalysisCarListView.setPresenter(this);
    }

    @Override
    public void getWaitToAnalysisCar(String userId, String carAnalysisState,String pageIndex, String pageSize) {
        mWaitToAnalysisCarListView.showLoading();

        Subscription subscription = mHttpService.getWaitAnalysisCarList(userId,carAnalysisState,pageIndex,pageSize)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("waitAnalysisCarList:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mWaitToAnalysisCarListView.dismissLoading();
                            mWaitToAnalysisCarListView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mWaitToAnalysisCarListView.dismissLoading();
                        mWaitToAnalysisCarListView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<CarInfo> carList = new ArrayList<CarInfo>();

                        if (jsonObject.get("data").isJsonNull()==false)
                        {
                            carList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<CarInfo>>(){});
                        }

                        mWaitToAnalysisCarListView.dismissLoading();
                        mWaitToAnalysisCarListView.showWaitToAnalysisCar(carList);
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
