package com.aofan.cardismantling.mvp.datatongji.selltongji;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.SellTongJiListItem;
import com.aofan.cardismantling.common.Constant;
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
 * Created by Administrator on 2017/1/5.
 */

public class SellTongJiPresenter implements SellTongJiContract.Presenter {


    @NonNull
    SellTongJiContract.View mSellTongJiView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public SellTongJiPresenter(@NonNull SellTongJiContract.View mSellTongJiView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mSellTongJiView = mSellTongJiView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mSellTongJiView.setPresenter(this);
    }


    @Override
    public void getSellTongJi(String userId, String startTime, String endTime,String pageIndex, String pageSize) {
        mSellTongJiView.showLoading();
        Subscription subscription = mHttpService.getSellTongJiData(userId,startTime,endTime,pageIndex,pageSize)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("sellTongJiResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mSellTongJiView.dismissLoading();
                            mSellTongJiView.onGetSellTongJiListError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mSellTongJiView.dismissLoading();
                        mSellTongJiView.onGetSellTongJiListError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        List<SellTongJiListItem> sellTongJiList = CommonUtil.formatJsonToList(jsonObject,"data",new TypeToken<List<SellTongJiListItem>>(){});
                        String sellCount = "0";
                        if (jsonObject.get("totalount").isJsonNull()==false)
                        {
                            sellCount = jsonObject.get("totalount").getAsString();
                        }

                        String sellValue = "0";

                        if (jsonObject.get("sumprice").isJsonNull()==false)
                        {
                            sellValue = jsonObject.get("sumprice").getAsString();
                        }

                        mSellTongJiView.dismissLoading();
                        mSellTongJiView.onGetSellTongJiListSuccess(sellTongJiList,sellCount,sellValue);
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
