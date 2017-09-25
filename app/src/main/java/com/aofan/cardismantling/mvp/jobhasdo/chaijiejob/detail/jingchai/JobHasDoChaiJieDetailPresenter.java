package com.aofan.cardismantling.mvp.jobhasdo.chaijiejob.detail.jingchai;

import android.support.annotation.NonNull;

import com.aofan.cardismantling.bean.ChaiJieDetailInfo;
import com.aofan.cardismantling.common.Constant;
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
 * Created by Administrator on 2016/11/29.
 */

public class JobHasDoChaiJieDetailPresenter implements JobHasDoChaiJieDetailContract.Presenter {


    @NonNull
    JobHasDoChaiJieDetailContract.View mJobHasDoChaiJieDetailView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final HttpService mHttpService;

    @NonNull
    private CompositeSubscription mSubscriptions;


    public JobHasDoChaiJieDetailPresenter(@NonNull JobHasDoChaiJieDetailContract.View mJobHasDoChaiJieDetailView, @NonNull BaseSchedulerProvider mSchedulerProvider, @NonNull HttpService mHttpService) {
        this.mJobHasDoChaiJieDetailView = mJobHasDoChaiJieDetailView;
        this.mSchedulerProvider = mSchedulerProvider;
        this.mHttpService = mHttpService;
        this.mSubscriptions = new CompositeSubscription();

        this.mJobHasDoChaiJieDetailView.setPresenter(this);
    }

    @Override
    public void getHasDoChaiJieDetail(String oId) {
        mJobHasDoChaiJieDetailView.showLoading();

        Subscription subscription = mHttpService.getChaiJiePaiGongDanDetail(oId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .filter(new Func1<JsonObject, Boolean>() {
                    @Override
                    public Boolean call(JsonObject jsonObject) {
                        LogUtil.e("chaiJiePaiGongDanDetailResponse:"+ jsonObject.toString());
                        if (!jsonObject.get(Constant.RESPONSE_KEY_STATUS).getAsString().equals(Constant.SUCCESS))
                        {
                            mJobHasDoChaiJieDetailView.dismissLoading();
                            mJobHasDoChaiJieDetailView.onGetHasDoChaiJieDetailError(jsonObject.get(Constant.RESPONSE_KEY_MESSAGE).getAsString());
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
                        mJobHasDoChaiJieDetailView.dismissLoading();
                        mJobHasDoChaiJieDetailView.onGetHasDoChaiJieDetailError(TipStr.TIP_SERVER_GET_DATA_WRONG);
                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {

                        ChaiJieDetailInfo chaiJieDetail = (ChaiJieDetailInfo) CommonUtil.formatJsonToObject(jsonObject,"data",new TypeToken<ChaiJieDetailInfo>(){});

                        mJobHasDoChaiJieDetailView.dismissLoading();
                        mJobHasDoChaiJieDetailView.onGetHasDoChaiJieDetailSuccess(chaiJieDetail);

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
