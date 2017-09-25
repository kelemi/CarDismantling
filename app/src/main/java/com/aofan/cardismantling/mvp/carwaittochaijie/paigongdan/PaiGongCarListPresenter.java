package com.aofan.cardismantling.mvp.carwaittochaijie.paigongdan;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.CarInfo;
import com.aofan.cardismantling.bean.CarOfPaiGongDan;
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
 * Created by Administrator on 2016/11/27.
 */

public class PaiGongCarListPresenter implements PaiGongCarListContract.Presenter {


    @NonNull
    PaiGongCarListContract.View mPaiGongCarListView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public PaiGongCarListPresenter(@NonNull PaiGongCarListContract.View mPaiGongCarListView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mPaiGongCarListView = mPaiGongCarListView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mPaiGongCarListView.setPresenter(this);
    }

    @Override
    public void getPaiGongCarList(String userId, String carPaiGongState,String pageIndex,String pageSize) {
        mPaiGongCarListView.showLoading();

        Subscription subscription = mHttpService.getPaiGongAboutCarList(userId,carPaiGongState,pageIndex,pageSize)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("carPaiGongAboutList:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mPaiGongCarListView.dismissLoading();
                            mPaiGongCarListView.showError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mPaiGongCarListView.dismissLoading();
                        mPaiGongCarListView.showError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<CarOfPaiGongDan> carOfPaiGongList =  new ArrayList<CarOfPaiGongDan>();

                        if (!jsonObject.get("data").isJsonNull())
                        {
                            carOfPaiGongList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<CarOfPaiGongDan>>(){});
                        }

                        mPaiGongCarListView.dismissLoading();
                        mPaiGongCarListView.showPaiGongCarList(carOfPaiGongList);

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
