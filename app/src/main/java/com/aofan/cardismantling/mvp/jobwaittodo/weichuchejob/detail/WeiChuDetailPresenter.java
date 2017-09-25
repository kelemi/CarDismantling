package com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.detail;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.common.Constant;
import com.aofan.cardismantling.common.TipStr;
import com.aofan.cardismantling.http.HttpService;
import com.aofan.cardismantling.mvp.jobwaittodo.weichuchejob.bean.WeiChuChaijieDetail;
import com.aofan.cardismantling.util.LogUtil;
import com.aofan.cardismantling.util.schedulers.BaseSchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import rx.Observer;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/11/23.
 */

public class WeiChuDetailPresenter implements WeiChuDetailContract.Presenter {


    @NonNull
    WeiChuDetailContract.View mMainView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public WeiChuDetailPresenter(@NonNull WeiChuDetailContract.View mMainView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mMainView = mMainView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mMainView.setPresenter(this);
    }

    @Override
    public void getChaiJieDetail(String did) {

        mMainView.showLoading();

        Subscription subscription = mHttpService.getChaiJieDetail(did)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("getChaiJieDetail:" + jsonObject.toString());
                        //{"staues":"sucess","message":"获取成功","data":{"oid":"187","partnamelist":"无","requirement":"无","dispatchperson":"zs","caption":"","cjtype":"粗拆","dispatchtime":null,"createtime":"2017-07-13 23:00:11","files":"无","createperson":"zs","comletetime":null,"comleteperson":null,"surecompetetime":null,"surecompeteperson":"0","completepersonname":"","surecompletepersonname":"","imgattachlist":[]}}
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS)) {
                            mMainView.dismissLoading();
                            mMainView.getChaiJieDetailFail(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        JsonObject object=jsonObject.get("data").getAsJsonObject();
                        WeiChuChaijieDetail weiChuChaijieDetail=new Gson().fromJson(object,WeiChuChaijieDetail.class);
                        mMainView.getChaiJieDetailSuccess(weiChuChaijieDetail);

                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void no_saveCompleteOrder(String did) {
        mMainView.showLoading();

        Subscription subscription = mHttpService.no_saveCompleteOrder(did)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("no_saveCompleteOrder:" + jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS)) {
                            mMainView.dismissLoading();
                            mMainView.saveCompleterFail(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mMainView.saveCompleterSuccess("提交成功");

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
