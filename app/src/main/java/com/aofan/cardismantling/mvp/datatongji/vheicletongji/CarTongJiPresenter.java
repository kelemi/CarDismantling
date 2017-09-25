package com.aofan.cardismantling.mvp.datatongji.vheicletongji;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.ChaiJieDetailInfo;
import com.aofan.cardismantling.bean.TongJiDataOfCar;
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
 * Created by Administrator on 2016/11/29.
 */

public class CarTongJiPresenter implements CarTongJiContract.Presenter {

    @NonNull
    CarTongJiContract.View mCarTongJiView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public CarTongJiPresenter(@NonNull CarTongJiContract.View mCarTongJiView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mCarTongJiView = mCarTongJiView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mCarTongJiView.setPresenter(this);
    }

    @Override
    public void getCarTongJiData(String userId,String pageIndex, String pageSize) {
        mCarTongJiView.showLoading();

        Subscription subscription = mHttpService.getCarTongJiData(userId,pageIndex,pageSize)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("carTongJiData:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mCarTongJiView.dismissLoading();
                            mCarTongJiView.onGetCarTongJiDataError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mCarTongJiView.dismissLoading();
                        mCarTongJiView.onGetCarTongJiDataError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        List<TongJiDataOfCar> carTongJiList = new ArrayList<TongJiDataOfCar>();

                        if (jsonObject.get("data").isJsonNull() == false)
                        {
                            carTongJiList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<TongJiDataOfCar>>(){});
                        }

                        mCarTongJiView.dismissLoading();
                        mCarTongJiView.onGetCarTongJiDataSuccess(carTongJiList);

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
